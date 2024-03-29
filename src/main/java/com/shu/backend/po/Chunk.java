package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("chunk")
public class Chunk {

    private Long id;

    @TableField(value = "corpus_id")
    private Long corpusId;

    @TableField(value = "doc_id")
    private Long docId;

    private String metadata;

    private String content;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
