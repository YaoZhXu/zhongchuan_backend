package com.shu.backend.vo.request.chat;

import com.shu.backend.vo.request.PageCommonReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/25 20:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryByUserIdReq extends PageCommonReq {
    @NotNull(message = "userId不能为空")
    private Long userId;
}
