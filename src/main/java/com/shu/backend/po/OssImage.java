package com.shu.backend.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oss_image_mappings")
public class OssImage {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "local_path")
    private String localPath;

    @TableField(value = "oss_url")
    private String ossUrl;
}
