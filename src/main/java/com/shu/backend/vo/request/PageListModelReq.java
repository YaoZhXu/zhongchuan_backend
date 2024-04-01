package com.shu.backend.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageListModelReq extends PageCommonReq {

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
