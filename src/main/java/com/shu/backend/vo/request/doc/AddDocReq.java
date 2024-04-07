package com.shu.backend.vo.request.doc;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddDocReq {

    @NotNull(message = "corpusId不能为空")
    private Long corpusId;

    @NotBlank(message = "docName不能为空")
    private String docName;

    @NotBlank(message = "path不能为空")
    private String path;
}
