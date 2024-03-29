package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.DirectoryStructure;

public interface DirectoryStructureService extends IService<DirectoryStructure> {

    boolean insert(Long actualId, Long parentId, Integer type);

    boolean delete(Long actualId, Long parentId, Integer type);
}
