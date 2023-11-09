package com.example.springboot.report;

import com.example.springboot.report.entity.MsgHeader;
import com.example.springboot.report.entity.RawDataKey;
import com.example.springboot.report.entity.TsTelemetryData;
import com.example.springboot.utils.ByteBufferUtils;
import com.example.springboot.utils.HexUtils;
import com.example.springboot.utils.TsPartitionDate;
import org.apache.commons.lang3.time.DateUtils;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Analysis
 * @author: luogongquan
 * @since: 2023/8/1 14:25
 */
public class Analysis {
    public static void main1(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(HexUtils.fromHexString("0200404a02898606191200706207290002000008000004000701d1743c06d20314000000000000230725150240140400000001150400000003160400" +
                "00000017020000e1" +
                "170000000117047d0205322e332e3001030000e85000005610ce7e"));
        MsgHeader header = analysisHeader(buffer);
        TsTelemetryData telemetryData = analysisBaseInfo(buffer, "test");
        analysisExtremum(buffer,"test",new Date().getTime());
    }


    public static void main(String[] args) {
    }
    private static void analysisExtremum(ByteBuffer buffer,
                                  String vin,Long ts) {
        int extremumId;
        int extremumLen;
        while (buffer.position() < buffer.limit()) {
            extremumId = toInt(buffer, 1);
            extremumLen = toInt(buffer, 1);
            switch (extremumId) {
                case 0xE1:
                    tempE2(buffer, vin, ts, extremumLen);
                    //System.out.println(ByteBufferUtils.toStr(buffer, extremumLen));
                    break;
                default:
                    //跳过指定长度
                    buffer.position(buffer.position() + extremumLen);
                    break;
            }
        }
    }

    private  static void tempE1(ByteBuffer buffer, String vin, long ts, int extremumLen) {

        StringBuilder sb = new StringBuilder();
        sb.append(" 附加信息长度:").append(extremumLen);
        sb.append(" 状态:").append(ByteBufferUtils.toInt(buffer, 4));
        sb.append(" 4G信号强度:").append(ByteBufferUtils.toInt(buffer, 1));
        sb.append(" 设备温度:").append(ByteBufferUtils.toInt(buffer, 2));
        int vn = ByteBufferUtils.toInt(buffer, 1);
        sb.append(" 设备版本信息长度:").append(vn);
        sb.append(" 设备版本信息:").append(ByteBufferUtils.toStr(buffer, vn));
        int sdn = ByteBufferUtils.toInt(buffer, 1);
        for (int i = 0; i < sdn; i++) {
            sb.append(" SD卡状态:").append(ByteBufferUtils.toInt(buffer, 1));
            sb.append(" SD卡总容量:").append(ByteBufferUtils.toLong(buffer, 4));
            sb.append(" SD卡剩余容量:").append(ByteBufferUtils.toLong(buffer, 4));
        }
        System.out.println(sb.toString());
    }
    private  static void tempE2(ByteBuffer buffer, String vin, long ts, int extremumLen) {

        int anInt = ByteBufferUtils.toInt(buffer, 4);
        int anInt1 = ByteBufferUtils.toInt(buffer, 1);
        int anInt2 = ByteBufferUtils.toInt(buffer, 2);
        //版本信息长度
        int vn = ByteBufferUtils.toInt(buffer, 1);
        String str = ByteBufferUtils.toStr(buffer, vn);
        //sd卡数量
        int sdn = ByteBufferUtils.toInt(buffer, 1);
        int readWriteStatus = 1;     //sd卡读写状态值0 Or 1,存在0则为0
        int storageStatus = 1;       //所有sd卡存储状态值0 Or 1,存在0则为0
        for (int i = 0; i < sdn; i++) {
            //读写状态与存储状态需转二进制获取对应位置的值
            int sdInt = ByteBufferUtils.toInt(buffer, 1);


            long aLong = ByteBufferUtils.toLong(buffer, 4);
            long aLong1 = ByteBufferUtils.toLong(buffer, 4);
        }
    }
    public static MsgHeader analysisHeader(ByteBuffer buffer) {
        MsgHeader header = new MsgHeader();
        //消息ID(2字节)
        header.setMsgId(toInt(buffer, 2));
        //消息体属性(2字节)
        header.setMulti((buffer.get() & 0x20) == 0x20);
        buffer.position(buffer.position() + 1);
        //协议版本号(1字节)
        buffer.position(buffer.position() + 1);
        //终端手机号(BCD[10])
        header.setMobile(toBcdStr(buffer, 10));
        //消息流水号(2字节)
        header.setSeq(toInt(buffer, 2));
        //消息包封装项
        if (header.isMulti()) {
            header.setPackageCount(toInt(buffer, 2));
            header.setPackageSeq(toInt(buffer, 2));
        }
        return header;
    }

    protected static int toInt(ByteBuffer buffer, int len) {
        int value = 0;
        for (int i = 0; i < len; i++) {
            value = (value << 8) | (buffer.get() & 0xFF);
        }
        return value;
    }



    protected static String toBcdStr(ByteBuffer buffer, int len) {
        byte[] bytes = new byte[len];
        buffer.get(bytes);
        String bcdStr = HexUtils.toHexString(bytes);
        int offset = 0;
        for (int i = 0; i < bcdStr.length(); i++) {
            if (bcdStr.charAt(i) != '0') {
                break;
            }
            offset++;
        }
        return bcdStr.substring(offset);
    }

    private static TsTelemetryData analysisBaseInfo(ByteBuffer buffer, String vin) {
        TsTelemetryData data = TsTelemetryData.builder()
                .flag(toInt(buffer, 4))
                .state(toInt(buffer, 4))
                .lat(toInt(buffer, 4))
                .lng(toInt(buffer, 4))
                .elev(toInt(buffer, 2))
                .speed(toInt(buffer, 2))
                .direction(toInt(buffer, 2)).build();
        long ts = toBcd(buffer, 6);
        data.setPk(createRawDataKey(vin, ts));
        return data;
    }
    protected static long toBcd(ByteBuffer buffer, int len) {
        long bcd = 0;
        byte _byte;
        for (int i = 0; i < len; i++) {
            _byte = buffer.get();
            int v1 = ((_byte & 0xFF) >> 4) % 10;//高4位
            int v2 = (_byte & 0x0F) % 10;//低4位
            bcd = bcd * 100 + v1 * 10 + v2;
        }
        return bcd;
    }
    private static RawDataKey createRawDataKey(String vin, long ts) {
        SimpleDateFormat sp = new SimpleDateFormat("yyMMddHHmmss");
        long partition = 0;
        if (ts > 99999999999L && ts < 1000000000000L) {//判断长度是否为8位
            try {
                partition = TsPartitionDate.WEEKS.toPartition(sp.parse(String.valueOf(ts)).getTime());
            } catch (ParseException e) {
            }
        }
        if (partition == 0) {
        }
        return RawDataKey.builder()
                .vin(vin)
                .partition(partition)
                .ts(ts)
                .build();
    }
}
