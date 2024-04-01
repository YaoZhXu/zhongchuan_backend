package com.shu.backend.vo;

import com.shu.backend.dto.Message;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ChatReq {

    @NotNull(message = "topicId不能为空")
    private Long topicId;

    private List<Message> messages;
}
