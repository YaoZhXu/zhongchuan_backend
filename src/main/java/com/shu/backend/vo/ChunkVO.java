package com.shu.backend.vo;

import lombok.Data;

@Data
public class ChunkVO {

    private Long chunkId;

    private Long corpusId;

    private Long docId;

    private String title;

    private String content;

    private Long pagination;

    private String createTime;

    private String updateTime;
}
