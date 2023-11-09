package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 车辆数据表
 */
@ToString
@Data
@Builder
public class TsTelemetryDataExtremum {

    private Integer kilometer; //里程
    private Integer oil;//油量
    private Integer speed;//速度
    private Integer eventId;//需要人工确认的报警事件ID
    private List<Integer> kpa;//胎压HEX
    private Integer temp;//车厢温度
    private Integer signalState;//扩展车辆信号状态位
    private Integer ioState;//IO状态位
    private Integer ad;//模拟量
    private Integer wifi;//无线通信网络信号强度
    private Integer gnss;//GNSS定位卫星数

}
