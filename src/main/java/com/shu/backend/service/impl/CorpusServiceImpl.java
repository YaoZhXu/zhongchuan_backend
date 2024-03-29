package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.constants.DirectoryEntryType;
import com.shu.backend.mapper.ChunkMapper;
import com.shu.backend.mapper.CorpusMapper;
import com.shu.backend.mapper.DirectoryStructureMapper;
import com.shu.backend.mapper.DocumentMapper;
import com.shu.backend.po.Chunk;
import com.shu.backend.po.Corpus;
import com.shu.backend.po.DirectoryStructure;
import com.shu.backend.po.Document;
import com.shu.backend.service.CorpusService;
import com.shu.backend.service.DirectoryStructureService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CorpusServiceImpl extends ServiceImpl<CorpusMapper, Corpus> implements CorpusService {

    @Resource
    private DocumentMapper documentMapper;

    @Resource
    private ChunkMapper chunkMapper;

    @Resource
    private DirectoryStructureMapper directoryStructureMapper;

    @Resource
    private DirectoryStructureService directoryStructureService;

    @Override
    public Page<Corpus> list(int pageNo, int pageSize) {
        Page<Corpus> p = new Page<>(pageNo, pageSize);
        return page(p);
    }

    @Override
    @Transactional
    public Long insert(String corpusName, String corpusDesc) {
        Corpus corpus = new Corpus();
        corpus.setName(corpusName);
        corpus.setCorpusDesc(corpusDesc);
        corpus.setCreateBy(UserContextHolder.getUserInfo().getUsername());
        corpus.setUpdateBy(UserContextHolder.getUserInfo().getUsername());
        save(corpus);

        Long corpusId = corpus.getId();
        if (corpusId == null) {
            return null;
        }

        directoryStructureService.insert(corpusId, 1L, DirectoryEntryType.CORPUS);
        return corpusId;
    }

    @Override
    @Transactional
    public boolean edit(Long corpusId, String corpusName, String corpusDesc) {
        if (corpusId == null || getById(corpusId) == null) {
            return false;
        }

        Corpus corpus = new Corpus();
        Optional.ofNullable(corpusName).ifPresent(corpus::setName);
        Optional.ofNullable(corpusDesc).ifPresent(corpus::setCorpusDesc);
        corpus.setUpdateBy(UserContextHolder.getUserInfo().getUsername());

        LambdaUpdateWrapper<Corpus> corpusWrapper = new LambdaUpdateWrapper<>();
        corpusWrapper.eq(Corpus::getId, corpusId);
        update(corpus, corpusWrapper);

        if (corpusName != null) {
            LambdaUpdateWrapper<DirectoryStructure> dsWrapper = new LambdaUpdateWrapper<>();
            dsWrapper.eq(DirectoryStructure::getActualId, corpusId);
            dsWrapper.eq(DirectoryStructure::getType, DirectoryEntryType.CORPUS);
            dsWrapper.set(DirectoryStructure::getName, corpusName);
            directoryStructureMapper.update(dsWrapper);
        }
        return true;
    }

    @Override
    public Corpus queryById(Long corpusId) {
        if (corpusId == null) {
            return null;
        }

        return getById(corpusId);
    }

    @Override
    @Transactional
    public boolean delete(Long corpusId) {
        if (corpusId == null) {
            return false;
        }

        Corpus corpus = getById(corpusId);
        if (corpus == null) {
            return false;
        }

        chunkMapper.delete(new LambdaUpdateWrapper<Chunk>().eq(Chunk::getCorpusId, corpusId));
        documentMapper.delete(new LambdaUpdateWrapper<Document>().eq(Document::getCorpusId, corpusId));
        removeById(corpusId);
        directoryStructureService.delete(corpusId, 1L, DirectoryEntryType.CORPUS);
        return true;
    }
}
