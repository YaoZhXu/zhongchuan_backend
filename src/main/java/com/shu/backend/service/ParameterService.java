package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Parameter;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 17:26
 */

public interface ParameterService extends IService<Parameter> {

    Parameter queryParameter(String userId);

    boolean edit(Parameter parameter);
}
