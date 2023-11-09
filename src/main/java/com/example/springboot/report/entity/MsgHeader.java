package com.example.springboot.report.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MsgHeader {
    private int msgId;//消息ID
    private String mobile;//手机号
    private int seq;//消息流水号
    private boolean multi;//true分包
    private int packageCount;//消息包总数
    private int packageSeq;//包序号
//    private int responseSeq;//应答流水号(-1表示没有应答流水号)

}
