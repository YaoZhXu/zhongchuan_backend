package com.shu.backend.vo;

import lombok.Data;

@Data
public class ListAllDocVO {

    private Long docId;

    private String docName;

    private String docType;

    private Long corpusId;

    private String corpusName;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;
}
