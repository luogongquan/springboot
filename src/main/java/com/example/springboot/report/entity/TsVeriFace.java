package com.example.springboot.report.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 人脸识别结果上报记录
 */
@Data
@Builder
public class TsVeriFace {

    private VeriFaceKey pk;

    /**
     * 设备账号(不作为查询依据)
     */
    @NotNull
    private String mobile;

    /**
     * 状态：1识别中、2识别失败、3平台识别成功、4设备识别成功
     */
    @NotNull
    private Integer status;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * DST人脸ID
     */
    private String dstFaceId;

    /**
     * 相匹配的人脸ID
     */
    private Long faceId;

    /**
     * 1采集照，2抓拍照
     */
    private Integer faceType;

    /**
     * (终端)数据ID
     */
    @NotNull
    private Integer dataId;

    /**
     * 文件标识UUID
     */
    @NotNull
    private String dataKey;

    /**
     * 是否检测到人脸
     */
    private Boolean isFace;

    /**
     * 服务端接收时间，13位时间戳
     */
    private Long serverTs;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TsVeriFace)) {
            return false;
        }
        return pk != null && pk.equals(((TsVeriFace) o).pk);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
