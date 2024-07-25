package com.shu.backend.vo.request.topic;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/19 18:50
 */
@Data
public class RenameTopicByTopicIdReq {

    @NotNull(message = "topicId不能为空")
    private Long topicId;

    @NotBlank(message = "修改名称不能为空")
    private String name;
}
