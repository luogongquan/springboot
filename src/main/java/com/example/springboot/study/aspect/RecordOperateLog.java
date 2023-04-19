package com.example.springboot.study.aspect;

import lombok.Data;

/**
 * @author：罗功权
 * @date：2023/3/22 11:49
 **/
@Data
public class RecordOperateLog {
    private int orderId;
    private String desc;
    private String name;
}
