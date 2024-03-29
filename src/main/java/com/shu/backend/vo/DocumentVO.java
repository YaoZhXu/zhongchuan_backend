package com.shu.backend.vo;

import lombok.Data;

@Data
public class DocumentVO {

    private Long docId;

    private Long corpusId;

    private String docName;

    private Integer docType;

    private String docPath;

    private Long chunkSize;

    private Integer vectorSize;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;
}
