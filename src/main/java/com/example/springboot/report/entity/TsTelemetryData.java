package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 原始数据解析
 */
@Data
@Builder
public class TsTelemetryData {

    private RawDataKey pk;

    @NotNull
    private Integer flag; //报警标志
    @NotNull
    private Integer state; //状态
    @NotNull
    private Integer lat; //纬度
    @NotNull
    private Integer lng; //经度
    @NotNull
    private Integer elev; //海拔高度
    @NotNull
    private Integer speed; //速度
    @NotNull
    private Integer direction; //方向

    @NotNull
    private TsTelemetryDataExtremum extremum;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TsTelemetryData)) {
            return false;
        }
        return pk != null && pk.equals(((TsTelemetryData) o).pk);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
