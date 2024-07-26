package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.RerankModel;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:37
 */
public interface RerankModelService extends IService<RerankModel> {
    Page<RerankModel> list(Integer pageNo, Integer pageSize, RerankModel rerankModel);

    Integer insert(RerankModel rerankModel);

    boolean update(RerankModel rerankModel);

    boolean delete(Integer id);
}
