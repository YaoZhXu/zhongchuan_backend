package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 19:37
 */
@Data
@TableName("logs")
public class Log {

    @TableId(type = IdType.AUTO)
    private BigInteger id;

    @TableField(value = "event_id")
    private String eventId;

    @TableField(value = "event_date")
    private LocalDateTime eventDate;

    private String thread;

    @TableField(value = "class")
    private String class_;

    private String method;

    private String message;

    private String exception;

    private String level;

    private LocalDateTime time;

}
