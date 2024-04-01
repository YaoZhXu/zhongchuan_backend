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

    private String uid;

    private String type;

    private String name;

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
}
