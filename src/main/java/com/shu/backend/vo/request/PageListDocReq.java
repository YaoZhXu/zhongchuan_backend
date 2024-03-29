package com.shu.backend.vo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageListDocReq extends PageCommonReq {

    @NotNull(message = "corpusId不能为空")
    private Long corpusId;
}
