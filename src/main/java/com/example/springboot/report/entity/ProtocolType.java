package com.example.springboot.report.entity;

public enum ProtocolType {
    JTT808_2013(12, 16),
    JTT808_2019(17, 21),
    ;
    /**
     * 不分包时包头长度
     */
    private int headerLen;
    /**
     * 分包时包头长度
     */
    private int multiHeaderLen;

    ProtocolType(int headerLen, int multiHeaderLen) {
        this.headerLen = headerLen;
        this.multiHeaderLen = multiHeaderLen;
    }

    /**
     * 不分包时包头长度
     */
    public int headerLen() {
        return headerLen;
    }

    /**
     * 分包时包头长度
     */
    public int multiHeaderLen() {
        return multiHeaderLen;
    }
}
