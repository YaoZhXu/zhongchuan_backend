package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.ChunkMapper;
import com.shu.backend.mapper.CorpusMapper;
import com.shu.backend.mapper.DirectoryStructureMapper;
import com.shu.backend.mapper.DocumentMapper;
import com.shu.backend.po.Chunk;
import com.shu.backend.po.Corpus;
import com.shu.backend.po.DirectoryStructure;
import com.shu.backend.po.Document;
import com.shu.backend.service.DirectoryStructureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Service
public class DirectoryStructureServiceImpl extends ServiceImpl<DirectoryStructureMapper, DirectoryStructure>
        implements DirectoryStructureService {

    @Resource
    private CorpusMapper corpusMapper;

    @Resource
    private DocumentMapper documentMapper;

    @Resource
    private ChunkMapper chunkMapper;

    @Override
    @Transactional
    public boolean insert(Long actualId, Long parentId, Integer type) {
        if (actualId == null || parentId == null || type == null) {
            return false;
        }

        DirectoryStructure record = getOne(new LambdaQueryWrapper<DirectoryStructure>()
                .eq(DirectoryStructure::getActualId, actualId)
                .eq(DirectoryStructure::getParentId, parentId));
        if (record != null) {
            return false;
        }

        DirectoryStructure structure = new DirectoryStructure();
        structure.setActualId(actualId);
        structure.setParentId(parentId);
        structure.setType(type);

        switch (type) {
            case 1:
                Corpus corpus = corpusMapper.selectById(actualId);
                if (corpus == null) {
                    return false;
                }

                structure.setName(corpus.getName());
                return save(structure);
            case 2:
                Document document = documentMapper.selectById(actualId);
                if (document == null) {
                    return false;
                }

                structure.setName(document.getName());
                return save(structure);
            case 3:
                Chunk chunk = chunkMapper.selectById(actualId);
                if (chunk == null) {
                    return false;
                }

                structure.setName("分片-" + chunk.getId());
                return save(structure);
            default:
                return false;
        }
    }

    @Override
    @Transactional
    public boolean delete(Long actualId, Long parentId, Integer type) {
        if (actualId == null || parentId == null || parentId == 0 || type == null) {
            return false;
        }

        Queue<DirectoryStructure> queue = new LinkedList<>();
        List<Long> toDeleted = new ArrayList<>();

        DirectoryStructure ds = getOne(new LambdaQueryWrapper<DirectoryStructure>()
                .eq(DirectoryStructure::getActualId, actualId)
                .eq(DirectoryStructure::getParentId, parentId)
                .eq(DirectoryStructure::getType, type));
        if (ds == null) {
            log.warn("有一条脏数据, actualId = {}, type = {}", actualId, type);
            return false;
        }
        queue.add(ds);

        while (!queue.isEmpty()) {
            DirectoryStructure ele = queue.poll();
            toDeleted.add(ele.getId());

            List<DirectoryStructure> children = list(new LambdaQueryWrapper<DirectoryStructure>()
                    .eq(DirectoryStructure::getParentId, ele.getActualId())
                    .eq(DirectoryStructure::getType, ele.getType() + 1));
            queue.addAll(children);
        }

        return removeBatchByIds(toDeleted);
    }
}
