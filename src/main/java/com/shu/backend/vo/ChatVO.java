package com.shu.backend.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatVO {

    private Long chatId;

    private Long topicId;

    private Long userId;

    private Long senderId;

    private String role;

    private String content;

    private LocalDateTime sendTime;

    private List<String> sourceLinks;

    private String review;
}
