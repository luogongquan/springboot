package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class TsSbAlarmFile {

    private SbAlarmFileKey pk;

    private Integer ftype; //文件类型
    private Integer size; //文件大小
    private Long finishTs;
    @NotNull
    private Byte finish; //0未完成，1已完成
    private List<String> upload; //已上传的文件块信息，数据元素格式“offset:len”
    private String dataKey; //ceph数据key

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TsSbAlarmFile)) {
            return false;
        }
        return pk != null && pk.equals(((TsSbAlarmFile) o).pk);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
