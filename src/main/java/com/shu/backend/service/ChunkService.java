package com.shu.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.backend.po.Chunk;

public interface ChunkService extends IService<Chunk> {

    Page<Chunk> list(int pageNo, int pageSize, Long docId);

    boolean edit(Long chunkId, String content);

    Chunk queryById(Long chunkId);

    boolean delete(Long chunkId);
}
