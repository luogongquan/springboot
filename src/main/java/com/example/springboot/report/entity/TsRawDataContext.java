package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 车辆数据表
 */
@ToString
@Data
@Builder
public class TsRawDataContext {

    private Integer source;
    private String domain;
    private String deviceId;
    private String mobile;
    private String vin;
    private Long ts;//服务器时间
    private String addrText;
}
