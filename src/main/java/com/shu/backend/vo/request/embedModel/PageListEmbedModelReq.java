package com.shu.backend.vo.request.embedModel;

import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 14:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageListEmbedModelReq extends PageCommonReq {

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
