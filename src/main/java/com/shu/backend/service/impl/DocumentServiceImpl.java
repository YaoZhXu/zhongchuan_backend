package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.constants.DirectoryEntryType;
import com.shu.backend.mapper.ChunkMapper;
import com.shu.backend.mapper.DocumentMapper;
import com.shu.backend.po.Chunk;
import com.shu.backend.po.Document;
import com.shu.backend.service.DirectoryStructureService;
import com.shu.backend.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {

    @Resource
    private ChunkMapper chunkMapper;

    @Resource
    private DirectoryStructureService directoryStructureService;

    @Override
    public Page<Document> list(int pageNo, int pageSize, Long corpusId, String docName) {
        LambdaQueryWrapper<Document> wrapper = new LambdaQueryWrapper<>();
        if (corpusId != null) {
            wrapper.eq(Document::getCorpusId, corpusId);
        }
        if (docName != null) {
            wrapper.like(Document::getName, docName);
        }

        Page<Document> p = new Page<>(pageNo, pageSize);
        return page(p, wrapper);
    }

    @Override
    public Long add(Long corpusId, String docName, String path) {
        if (corpusId == null || docName == null || path == null) {
            return null;
        }

        Document document = new Document();
        document.setCorpusId(corpusId);
        document.setName(docName);
        document.setPath(path);
        save(document);
        return document.getId();
    }

    @Override
    @Transactional
    public boolean delete(Long docId) {
        if (docId == null) {
            return false;
        }

        Document doc = getById(docId);
        if (doc == null) {
            return true;
        }
        Long corpusId = doc.getCorpusId();
        if (corpusId == null) {
            log.warn("有一条脏数据在Document表, docId = {}", docId);
            return false;
        }

        chunkMapper.delete(new LambdaQueryWrapper<Chunk>().eq(Chunk::getDocId, docId));
        removeById(docId);
        directoryStructureService.delete(docId, corpusId, DirectoryEntryType.DOCUMENT);
        return true;
    }
}
