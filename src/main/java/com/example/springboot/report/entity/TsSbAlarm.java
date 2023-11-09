package com.example.springboot.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 苏标报警数据
 */
@Data
@Builder
@AllArgsConstructor
public class TsSbAlarm {

    private SbAlarmKey pk;

    @NotNull
    private Long tsEnd;

    @NotNull
    private Integer dtype; //数据类型
    @NotNull
    private Integer alarmId; //终端上报的报警ID

    private Integer flag; //报警/事件类型
    private Integer level; //报警级别
    private Integer fatigue; //疲劳程度
    private Integer frontSpeed; //前车车速
    private Integer frontDistance; //前车/行人距离
    private Integer diverge; //偏离类型
    private Integer roadFlag; //道路标志识别类型
    private Integer roadFlagV; //道路标志识别数据

    @NotNull
    private Integer speed; //车速
    @NotNull
    private Integer elev; //海拔高度
    @NotNull
    private Integer lat; //纬度
    @NotNull
    private Integer lng; //经度
    @NotNull
    private Integer vehicleState; //车辆状态
    @NotNull
    private String tid; //终端ID
    @NotNull
    private Integer fc; //附件数量
    private List<TsSbAlarmTire> tires;

    private Integer turnLight; //转向灯状态
    private String dstFaceId; //DST人脸ID

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TsSbAlarm)) {
            return false;
        }
        return pk != null && pk.equals(((TsSbAlarm) o).pk);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
