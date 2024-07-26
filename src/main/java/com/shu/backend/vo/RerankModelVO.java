package com.shu.backend.vo;

import lombok.Data;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:22
 */
@Data
public class RerankModelVO {

    private Integer id;

    private String modelName;

    private String modelId;

    private String modelUrl;

    private String modelLang;

    private String modelType;

    private String modelDescription;

    private String createTime;

    private String updateTime;

    private String createBy;

    private String updateBy;
}
