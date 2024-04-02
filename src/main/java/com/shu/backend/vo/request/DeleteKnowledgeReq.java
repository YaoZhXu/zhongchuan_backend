package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteKnowledgeReq {

    @NotNull(message = "knowledgeId不能为空")
    private Long knowledgeId;
}
