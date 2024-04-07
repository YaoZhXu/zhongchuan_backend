package com.shu.backend.vo.converter;

import com.shu.backend.po.Chunk;
import com.shu.backend.vo.ChunkVO;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;

public class ChunkConverter {

    public static ChunkVO convertChunkToChunkVO(Chunk source) {
        if (source == null) {
            return null;
        }

        ChunkVO target = new ChunkVO();

        target.setChunkId(source.getId());
        target.setCorpusId(source.getCorpusId());
        target.setDocId(source.getDocId());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setPagination(source.getPagination());
        target.setCreateTime(localDatetime2String(source.getCreateTime()));
        target.setUpdateTime(localDatetime2String(source.getUpdateTime()));

        return target;
    }

    public static List<ChunkVO> convertChunkListToChunkVOList(List<Chunk> sourceList) {
        if (sourceList.isEmpty()) {
            return null;
        }

        List<ChunkVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertChunkToChunkVO(source)));

        return targetList;
    }
}
