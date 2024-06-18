package com.shu.backend.vo;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 20:20
 */

@Data
public class LogVO {
    private BigInteger id;

    private String eventId;

    private String eventDate;

    private String thread;

    private String class_;

    private String function;

    private String message;

    private String exception;

    private String level;

    private String time;
}
