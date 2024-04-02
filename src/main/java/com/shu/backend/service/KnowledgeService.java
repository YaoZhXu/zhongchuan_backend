package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Knowledge;

public interface KnowledgeService extends IService<Knowledge> {

    Long insert(Knowledge knowledge);

    boolean edit(Knowledge knowledge);

    boolean delete(Long knowledgeId);

    Knowledge queryById(Long knowledgeId);

    Page<Knowledge> list(int pageNo, int pageSize, Knowledge knowledge);
}
