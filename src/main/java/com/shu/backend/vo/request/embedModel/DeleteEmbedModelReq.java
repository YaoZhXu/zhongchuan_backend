package com.shu.backend.vo.request.embedModel;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 15:24
 */
@Data
public class DeleteEmbedModelReq {
    @NotNull(message = "id不能为空")
    private Integer id;
}
