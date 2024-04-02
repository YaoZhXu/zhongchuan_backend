package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.CorpusMapper;
import com.shu.backend.mapper.DocumentMapper;
import com.shu.backend.mapper.KnowledgeMapper;
import com.shu.backend.po.Document;
import com.shu.backend.po.Knowledge;
import com.shu.backend.service.KnowledgeService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeService {

    @Resource
    private CorpusMapper corpusMapper;

    @Resource
    private DocumentMapper documentMapper;

    @Override
    public Long insert(Knowledge knowledge) {
        if (StringUtils.isBlank(knowledge.getName()) || StringUtils.isBlank(knowledge.getVsType()) ||
                StringUtils.isBlank(knowledge.getEmbedModel()) || knowledge.getCorpusId() == null ||
                corpusMapper.selectById(knowledge.getCorpusId()) == null) {
            return null;
        }

        // TODO 调用向量库

        Long corpusId = knowledge.getCorpusId();
        Long count = documentMapper.selectCount(new LambdaQueryWrapper<Document>().eq(Document::getCorpusId, corpusId));

        knowledge.setFileCount(count);
        knowledge.setCreateBy(UserContextHolder.getUserInfo().getUsername());
        knowledge.setUpdateBy(UserContextHolder.getUserInfo().getUsername());
        save(knowledge);
        return knowledge.getId();
    }

    @Override
    public boolean edit(Knowledge knowledge) {
        if (knowledge.getId() == null || getById(knowledge.getId()) == null) {
            return false;
        }

        LambdaUpdateWrapper<Knowledge> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Knowledge::getId, knowledge.getId());
        if (StringUtils.isNotBlank(knowledge.getName())) {
            wrapper.set(Knowledge::getName, knowledge.getName());
        }
        if (StringUtils.isNotBlank(knowledge.getInfo())) {
            wrapper.set(Knowledge::getInfo, knowledge.getInfo());
        }
        if (StringUtils.isNotBlank(knowledge.getVsType())) {
            wrapper.set(Knowledge::getVsType, knowledge.getVsType());
        }
        if (StringUtils.isNotBlank(knowledge.getEmbedModel())) {
            wrapper.set(Knowledge::getEmbedModel, knowledge.getEmbedModel());
        }
        if (knowledge.getCorpusId() != null) {
            wrapper.set(Knowledge::getCorpusId, knowledge.getCorpusId());
        }

        // TODO 调用向量库

        wrapper.set(Knowledge::getUpdateBy, UserContextHolder.getUserInfo().getUsername());
        return update(wrapper);
    }

    @Override
    public boolean delete(Long knowledgeId) {
        if (knowledgeId == null || getById(knowledgeId) == null) {
            return false;
        }

        // TODO 调用向量库

        return removeById(knowledgeId);
    }

    @Override
    public Knowledge queryById(Long knowledgeId) {
        if (knowledgeId == null) {
            return null;
        }

        return getById(knowledgeId);
    }

    @Override
    public Page<Knowledge> list(int pageNo, int pageSize, Knowledge knowledge) {
        LambdaQueryWrapper<Knowledge> wrapper = new LambdaQueryWrapper<>();
        if (knowledge.getId() != null) {
            wrapper.eq(Knowledge::getId, knowledge.getId());
        }
        if (StringUtils.isNotBlank(knowledge.getName())) {
            wrapper.like(Knowledge::getName, knowledge.getName());
        }
        if (StringUtils.isNotBlank(knowledge.getInfo())) {
            wrapper.like(Knowledge::getInfo, knowledge.getInfo());
        }
        if (StringUtils.isNotBlank(knowledge.getVsType())) {
            wrapper.like(Knowledge::getVsType, knowledge.getVsType());
        }
        if (StringUtils.isNotBlank(knowledge.getEmbedModel())) {
            wrapper.like(Knowledge::getEmbedModel, knowledge.getEmbedModel());
        }
        if (knowledge.getCorpusId() != null) {
            wrapper.eq(Knowledge::getCorpusId, knowledge.getCorpusId());
        }

        Page<Knowledge> p = new Page<>(pageNo, pageSize);
        return page(p, wrapper);
    }
}
