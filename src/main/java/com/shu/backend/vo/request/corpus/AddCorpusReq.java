package com.shu.backend.vo.request.corpus;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddCorpusReq {

    @NotBlank(message = "corpusName不能为空")
    private String corpusName;

    private String corpusDesc;
}
