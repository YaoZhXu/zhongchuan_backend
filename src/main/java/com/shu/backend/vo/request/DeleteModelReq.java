package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteModelReq {

    @NotNull(message = "modelId不能为空")
    private Long modelId;
}
