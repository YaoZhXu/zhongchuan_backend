package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("chunk")
public class Chunk {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "corpus_id")
    private Long corpusId;

    @TableField(value = "doc_id")
    private Long docId;

    private String title;

    private String content;

    private Long pagination;

    @TableField(value = "image_url")
    private String imageUrl;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
