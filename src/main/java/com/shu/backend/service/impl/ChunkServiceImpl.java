package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.constants.DirectoryEntryType;
import com.shu.backend.mapper.ChunkMapper;
import com.shu.backend.mapper.DocumentMapper;
import com.shu.backend.po.Chunk;
import com.shu.backend.po.Document;
import com.shu.backend.service.ChunkService;
import com.shu.backend.service.DirectoryStructureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class ChunkServiceImpl extends ServiceImpl<ChunkMapper, Chunk> implements ChunkService {

    @Resource
    private DirectoryStructureService directoryStructureService;

    @Resource
    private DocumentMapper documentMapper;

    @Override
    public Page<Chunk> list(int pageNo, int pageSize, Long docId) {
        if (docId == null) {
            throw new RuntimeException("docId不能为空");
        }

        Page<Chunk> p = new Page<>(pageNo, pageSize);
        return page(p, new LambdaQueryWrapper<Chunk>().eq(Chunk::getDocId, docId));
    }

    @Override
    @Transactional
    public Long add(Long corpusId, Long docId, String title, String content, Long pagination, String imageUrl) {
        if (corpusId == null || docId == null || title == null || content == null || pagination == null) {
            return null;
        }

        if (documentMapper.selectById(docId) == null) {
            return null;
        }

        Chunk chunk = new Chunk();
        chunk.setCorpusId(corpusId);
        chunk.setDocId(docId);
        chunk.setTitle(title);
        chunk.setContent(content);
        chunk.setPagination(pagination);
        if (imageUrl != null) {
            chunk.setImageUrl(imageUrl);
        }

        save(chunk);
        documentMapper.update(new LambdaUpdateWrapper<Document>()
                .eq(Document::getId, docId).eq(Document::getCorpusId, corpusId)
                .set(Document::getChunkSize, documentMapper.selectById(docId).getChunkSize() + 1));

        return chunk.getId();
    }

    @Override
    public boolean edit(Long chunkId, String content) {
        if (chunkId == null) {
            return false;
        }

        if (content == null) {
            return true;
        }

        LambdaUpdateWrapper<Chunk> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Chunk::getId, chunkId);
        wrapper.set(Chunk::getContent, content);
        return update(wrapper);
    }

    @Override
    public Chunk queryById(Long chunkId) {
        if (chunkId == null) {
            return null;
        }

        return getById(chunkId);
    }

    @Override
    @Transactional
    public boolean delete(Long chunkId) {
        if (chunkId == null) {
            return false;
        }

        Chunk chunk = getById(chunkId);
        if (chunk == null) {
            return false;
        }

        Long docId = chunk.getDocId();
        if (docId == null) {
            log.warn("有一条脏数据在Chunk表, chunkId = {}", chunkId);
            return false;
        }

        removeById(chunkId);
        directoryStructureService.delete(chunkId, docId, DirectoryEntryType.CHUNK);
        return true;
    }
}
