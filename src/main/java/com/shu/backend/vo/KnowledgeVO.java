package com.shu.backend.vo;

import lombok.Data;

@Data
public class KnowledgeVO {

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
