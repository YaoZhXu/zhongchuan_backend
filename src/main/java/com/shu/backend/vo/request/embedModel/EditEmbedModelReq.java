package com.shu.backend.vo.request.embedModel;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 15:12
 */
@Data
public class EditEmbedModelReq {

    @NotNull(message = "id不能为空")
    private Integer id;

    private String modelName;

    private String modelId;

    private String modelUrl;

    private String modelLang;

    private String modelDimensions;

    private String modelMaxTokens;

    private String modelDescription;
}
