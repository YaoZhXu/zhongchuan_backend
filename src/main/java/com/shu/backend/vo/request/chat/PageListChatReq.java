package com.shu.backend.vo.request.chat;

import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/25 20:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageListChatReq extends PageCommonReq {

    private Long chatId;

    private Long topicId;

    private Long userId;

    private String role;

    private String content;

    private LocalDateTime sendTime;
}
