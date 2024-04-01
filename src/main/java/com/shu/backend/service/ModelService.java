package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Model;

public interface ModelService extends IService<Model> {

    Long insert(Model model);

    boolean edit(Model model);

    boolean delete(Long modelId);

    Model queryById(Long modelId);

    Page<Model> list(int pageNo, int pageSize, Model model);
}
