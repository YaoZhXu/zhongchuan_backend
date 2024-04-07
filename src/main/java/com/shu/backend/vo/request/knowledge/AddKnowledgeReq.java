package com.shu.backend.vo.request.knowledge;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddKnowledgeReq {

    @NotBlank(message = "knowledgeName不能为空")
    private String knowledgeName;

    private String knowledgeInfo;

    @NotBlank(message = "vsType不能为空")
    private String vsType;

    @NotBlank(message = "embedModel不能为空")
    private String embedModel;

    @NotNull(message = "corpusId不能为空")
    private Long corpusId;
}
