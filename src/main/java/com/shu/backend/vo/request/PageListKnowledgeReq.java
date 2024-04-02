package com.shu.backend.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageListKnowledgeReq extends PageCommonReq {

    private Long knowledgeId;

    private String knowledgeName;

    private String knowledgeInfo;

    private String vsType;

    private String embedModel;

    private Long fileCount;

    private Long corpusId;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;
}
