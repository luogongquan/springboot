package com.example.springboot.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 车辆数据表
 */
@ToString
@Data
@Builder
@AllArgsConstructor
public class TsSbAlarmTire {

    private Integer position;//胎压报警位置
    private Integer event;//报警/事件类型
    private Integer kpa;//胎压
    private Integer temp;//胎温
    private Integer soc;//电池电量
}
