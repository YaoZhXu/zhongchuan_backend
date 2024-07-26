package com.shu.backend.vo;

import lombok.Data;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 12:08
 */

@Data
public class EmbedModelVO {
    private Integer id;

    private String modelName;

    private String modelEmbedId;

    private String modelDimensions;

    private String modelMaxTokens;

    private String modelLang;

    private String modelId;

    private String modelUrl;

    private String modelDescription;

    private String createBy;

    private String updateBy;

    private String createdAt;

    private String updatedAt;

}
