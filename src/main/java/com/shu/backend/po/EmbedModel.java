package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 12:02
 */
@Data
@TableName("embed_model")
public class EmbedModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("nick_name")
    private String nickName;

    @TableField("embed_id")
    private String embedId;

    private String dimensions;

    @TableField("max_tokens")
    private String maxTokens;

    private String lang;

    @TableField("model_id")
    private String modelId;

    @TableField("model_url")
    private String modelUrl;

    private String description;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("create_by")
    private String createBy;

    @TableField("update_by")
    private String updateBy;
}
