package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.EmbedModel;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 14:30
 */
public interface EmbedModelService extends IService<EmbedModel> {
    Page<EmbedModel> list(Integer pageNo, Integer pageSize, EmbedModel embedModel);

    Integer insert(EmbedModel embedModel);

    boolean update(EmbedModel embedModel);

    boolean delete(Integer id);
}
