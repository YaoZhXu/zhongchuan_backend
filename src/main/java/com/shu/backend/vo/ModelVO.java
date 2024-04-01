package com.shu.backend.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModelVO {

    private Long modelId;

    private String modelUid;

    private String modelType;

    private String modelName;

    private String modelLang;

    private String modelAbility;

    private String modelDescription;

    private String modelFormat;

    private BigDecimal modelSizeInBillions;

    private String quantization;

    private String revision;

    private Long contextLength;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;
}
