package com.shu.backend.vo.request.rerankModel;

import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageListRerankModelReq  extends PageCommonReq {

    private Integer id;

    private String modelName;

    private String modelId;

    private String modelUrl;

    private String modelLang;

    private String modelType;

    private String modelDescription;

    private String createBy;

    private String updateBy;

}
