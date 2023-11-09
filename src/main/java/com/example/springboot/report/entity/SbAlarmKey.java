package com.example.springboot.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@ToString
public class SbAlarmKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 20)
    private String vin;

    @NotNull
    private Long ts;

    @NotNull
    private Integer seq;

    @NotNull
    private Integer state; //标志状态

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        SbAlarmKey other = (SbAlarmKey) o;
        if (vin == null) {
            if (other.vin != null)
                return false;
        } else if (!vin.equals(other.vin))
            return false;
        if (ts == null) {
            return other.ts == null;
        } else if (!ts.equals(other.ts))
            return false;
        if (seq == null) {
            return other.seq == null;
        } else return seq.equals(other.seq);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
