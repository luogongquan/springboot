package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@Data
@Builder
public class RawDataKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    private String vin;

    @NotNull
    private Long partition;

    @NotNull
    private Long ts;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        RawDataKey other = (RawDataKey) o;
        if (vin == null) {
            if (other.vin != null)
                return false;
        } else if (!vin.equals(other.vin))
            return false;
        if (partition == null) {
            if (other.partition != null)
                return false;
        } else if (!partition.equals(other.partition))
            return false;
        if (ts == null) {
            return other.ts == null;
        } else return ts.equals(other.ts);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
