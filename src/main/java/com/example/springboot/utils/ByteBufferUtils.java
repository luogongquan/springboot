package com.example.springboot.utils;

import org.apache.tomcat.util.buf.HexUtils;

import java.nio.ByteBuffer;

public class ByteBufferUtils {

    public static int toInt(ByteBuffer buffer, int len) {
        int value = 0;
        for (int i = 0; i < len; i++) {
            value = (value << 8) | (buffer.get() & 0xFF);
        }
        return value;
    }

    public static long toLong(ByteBuffer buffer, int len) {
        long value = 0;
        for (int i = 0; i < len; i++) {
            value = (value << 8) | (buffer.get() & 0xFF);
        }
        return value;
    }

    public static long toBcd(ByteBuffer buffer, int len) {
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

    public static String toBcdStr(ByteBuffer buffer, int len) {
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

    public static String toStr(ByteBuffer buffer, int len) {
        byte[] bytes = new byte[len];
        buffer.get(bytes);
        return new String(bytes);
    }

}
