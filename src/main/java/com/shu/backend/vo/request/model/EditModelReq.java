package com.shu.backend.vo.request.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EditModelReq {

    @NotNull(message = "modelId不能为空")
    private Long modelId;

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

    private String isRun;
}
