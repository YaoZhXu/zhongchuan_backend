package com.shu.backend.vo.request.embedModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 14:56
 */
@Data
public class AddEmbedModelReq {
    @NotBlank(message = "modelName不能为空")
    private String modelName;

    private String modelId;

    private String modelUrl;

    private String modelLang;

    private String modelDimensions;

    private String modelMaxTokens;

    private String modelDescription;
}
