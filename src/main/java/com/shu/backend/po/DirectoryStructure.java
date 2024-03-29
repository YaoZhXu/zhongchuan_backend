package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("directory_structure")
public class DirectoryStructure {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "parent_id")
    private Long parentId;

    private String name;

    private Integer type;

    @TableField(value = "actual_id")
    private Long actualId;
}
