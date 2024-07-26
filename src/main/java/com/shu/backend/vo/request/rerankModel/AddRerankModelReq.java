package com.shu.backend.vo.request.rerankModel;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:59
 */
@Data
public class AddRerankModelReq {
    @NotBlank(message = "modelName不能为空")
    private String modelName;

    private String modelId;

    private String modelUrl;

    private String modelType;

    private String modelLang;

    private String modelDescription;
}
