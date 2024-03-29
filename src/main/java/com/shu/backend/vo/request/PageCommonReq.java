package com.shu.backend.vo.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PageCommonReq {

    @NotNull(message = "pageNo不能为空")
    @Min(value = 1, message = "页数从第一页开始")
    private Integer pageNo;

    @NotNull(message = "pageSize不能为空")
    @Min(value = 1, message = "每页记录数必须大于0")
    private Integer pageSize;
}
