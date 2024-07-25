package com.shu.backend.vo.request.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EditModelReq {

    @NotNull(message = "Id不能为空")
    private Long id;

    private String modelType;

    private String modelName;

    private String modelId;

    private String modelFamily;

    private String modelUri;

    private String modelFileNameTemplate;

    private String modelLang;

    private String modelAbility;

    private String modelDescription;

    private String modelFormat;

    private BigDecimal modelSizeInBillions;

    private String quantization;

    private String revision;

    private Long contextLength;

    private String isRun;

    private String modelPromptStyle;
}
