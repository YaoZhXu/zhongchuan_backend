package com.shu.backend.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicVO {

    private Long topicId;

    private String topicName;

    private Long userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
