package com.shu.backend.vo.request.topic;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClearTopicByTopicIdReq {

    @NotNull(message = "topicId不能为空")
    private Long topicId;
}
