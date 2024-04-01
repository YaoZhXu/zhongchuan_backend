package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddModelReq {

    @NotBlank(message = "modelType不能为空")
    private String modelType;

    @NotBlank(message = "modelName不能为空")
    private String modelName;

    @NotBlank(message = "modelLang不能为空")
    private String modelLang;

    @NotBlank(message = "modelAbility不能为空")
    private String modelAbility;

    private String modelDescription;

    @NotBlank(message = "modelFormat不能为空")
    private String modelFormat;

    @NotNull(message = "modelSizeInBillions不能为空")
    private BigDecimal modelSizeInBillions;

    private String quantization;

    private String revision;

    @NotNull(message = "contextLength不能为空")
    private Long contextLength;
}
