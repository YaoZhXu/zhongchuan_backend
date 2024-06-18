package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Log;

import java.math.BigInteger;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 19:35
 */
public interface LogService extends IService<Log> {

    Page<Log> list(int pageNo, int pageSize, Log log);

    boolean delete(BigInteger logId);

}
