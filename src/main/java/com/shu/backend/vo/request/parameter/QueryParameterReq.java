package com.shu.backend.vo.request.parameter;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 17:12
 */
@Data
public class QueryParameterReq {

    @NotNull(message = "UserID不能为空")
    private String userId;

    private String option;

    private String temperature;

    private String mulDialog;

    private String isRerank;

    private String isStream;
}