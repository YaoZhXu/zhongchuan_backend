package com.shu.backend.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 16:57
 */

@Data
public class ParameterVO {
    private String userId;

    private String option;

    private String temperature;

    private String mulDialog;

    private String isRerank;

    private String isStream;
}
