package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@ToString
public class VeriFaceKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    private String vin;

    /**
     * 设备端时间YYMMDDHHMMSS
     */
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
        VeriFaceKey other = (VeriFaceKey) o;
        if (vin == null) {
            if (other.vin != null)
                return false;
        } else if (!vin.equals(other.vin))
            return false;
        if (ts == null) {
            return other.ts == null;
        } else if (!ts.equals(other.ts))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
