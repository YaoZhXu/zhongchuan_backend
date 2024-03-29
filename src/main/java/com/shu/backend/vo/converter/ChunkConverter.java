package com.shu.backend.vo.converter;

import com.shu.backend.po.Chunk;
import com.shu.backend.vo.ChunkVO;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtil.localDatetime2String;

public class ChunkConverter {

    public static ChunkVO convertChunkToChunkVO(Chunk source) {
        ChunkVO target = new ChunkVO();

        target.setChunkId(source.getId());
        target.setCorpusId(source.getCorpusId());
        target.setDocId(source.getDocId());
        target.setContent(source.getContent());
        target.setCreateTime(localDatetime2String(source.getCreateTime()));
        target.setUpdateTime(localDatetime2String(source.getUpdateTime()));

        return target;
    }

    public static List<ChunkVO> convertChunkListToChunkVOList(List<Chunk> sourceList) {
        List<ChunkVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertChunkToChunkVO(source)));

        return targetList;
    }
}
