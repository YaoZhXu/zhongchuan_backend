package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("chat")
public class Chat {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "topic_id")
    private Long topicId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "sender_id")
    private Long senderId;

    private String role;

    private String content;

    @TableField(value = "send_time")
    private LocalDateTime sendTime;

    @TableField(value = "source_links")
    private String sourceLinks;

    private String review;
}
