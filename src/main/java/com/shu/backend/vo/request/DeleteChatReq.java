package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteChatReq {

    @NotNull(message = "chatId不能为空")
    private Long chatId;
}
