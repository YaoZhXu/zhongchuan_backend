package com.shu.backend.vo.request.log;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 21:45
 */

@Data
public class DeleteLogReq {

    @NotNull(message = "logId不能为空")
    private BigInteger logId;//请求参数
}
