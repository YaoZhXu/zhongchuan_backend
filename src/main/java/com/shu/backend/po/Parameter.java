package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 16:51
 */

@Data
@TableName("config")
public class Parameter {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "`option`")
    private String option;

    private String temperature;

    @TableField(value = "mul_dialog")
    private String mulDialog;

    @TableField(value = "is_rerank")
    private String isRerank;

    @TableField(value = "is_stream")
    private String isStream;
}
