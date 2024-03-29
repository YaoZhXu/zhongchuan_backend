package com.shu.backend.vo;

import lombok.Data;

@Data
public class CorpusVO {

    private Long corpusId;

    private String corpusName;

    private String corpusDesc;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;
}
