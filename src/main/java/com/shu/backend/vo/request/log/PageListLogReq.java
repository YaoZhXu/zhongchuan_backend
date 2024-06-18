package com.shu.backend.vo.request.log;

import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 20:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageListLogReq extends PageCommonReq {
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
