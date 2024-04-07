package com.shu.backend.vo.request.corpus;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditCorpusReq {

    @NotNull(message = "corpusId不能为空")
    private Long corpusId;

    private String corpusName;

    private String corpusDesc;
}
