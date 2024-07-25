package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("model")
public class Model {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "nick_name")
    private String nickName;

    private String type;

    @TableField(value = "llm_id")
    private String llmId;

    private String lang;

    private String ability;

    @TableField(value = "model_desc")
    private String modelDesc;

    private String format;

    @TableField(value = "size_in_billions")
    private BigDecimal sizeInBillions;

    private String quantization;

    private String revision;

    @TableField(value = "context_length")
    private Long contextLength;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "is_run")
    private String isRun;

    @TableField(value = "model_family")
    private String modelFamily;

    @TableField(value = "model_id")
    private String modelId;

    @TableField(value = "model_uri")
    private String modelUri;

    @TableField(value = "model_file_name_template")
    private String modelFileNameTemplate;

    @TableField(value = "prompt_style")
    private String promptStyle;

}
