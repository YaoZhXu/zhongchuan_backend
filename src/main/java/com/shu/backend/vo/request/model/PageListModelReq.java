package com.shu.backend.vo.request.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageListModelReq extends PageCommonReq {

    private Long id;

    private String modelNickName;

    private String modelType;

    private String modelLlmId;

    private String modelLang;

    private String modelAbility;

    private String modelDesc;

    private String modelFormat;

    private BigDecimal modelSizeInBillions;

    private String modelQuantization;

    private String revision;

    private Long contextLength;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private String isRun;

    private String modelFamily;

    private String modelId;

    private String modelUri;

    private String modelFileNameTemplate;

    private String modelPromptStyle;
}
