package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.nio.ByteBuffer;

/**
 * 原始数据
 */
@Data
@Builder
public class TsRawData {

    private RawDataKey pk;

    @NotNull
    private TsRawDataContext context;

    @NotNull
    private ByteBuffer data;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TsRawData)) {
            return false;
        }
        return pk != null && pk.equals(((TsRawData) o).pk);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
