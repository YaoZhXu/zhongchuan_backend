package com.shu.backend.vo.request.rerankModel;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 21:18
 */
@Data
public class DeleteRerankModelReq {
    @NotNull(message = "id不能为空")
    private Integer id;
}
