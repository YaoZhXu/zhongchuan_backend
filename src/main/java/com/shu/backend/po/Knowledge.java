package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("knowledge")
public class Knowledge {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String info;

    @TableField(value = "vs_type")
    private String vsType;

    @TableField(value = "embed_model")
    private String embedModel;

    @TableField(value = "file_count")
    private Long fileCount;

    @TableField(value = "corpus_id")
    private Long corpusId;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
