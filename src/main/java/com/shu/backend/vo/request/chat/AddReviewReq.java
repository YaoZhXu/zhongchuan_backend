package com.shu.backend.vo.request.chat;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddReviewReq {

    @NotNull(message = "topicId不能为空")
    private Long topicId;

    @NotNull(message = "content不能为空")
    private String content;

    @Size(max = 255, message = "最多评价255个字")
    private String review;
}
