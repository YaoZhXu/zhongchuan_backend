package com.shu.backend.vo.request.rerankModel;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 21:09
 */
@Data
public class EditRerankModelReq {
    @NotNull(message = "id不能为空")
    private Integer id;

    private String modelName;

    private String modelId;

    private String modelUrl;

    private String modelLang;

    private String modelType;

    private String modelDescription;
}
