package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditKnowledgeReq {

    @NotNull(message = "knowledgeId不能为空")
    private Long knowledgeId;

    private String knowledgeName;

    private String knowledgeInfo;

    private String vsType;

    private String embedModel;

    private Long corpusId;
}
