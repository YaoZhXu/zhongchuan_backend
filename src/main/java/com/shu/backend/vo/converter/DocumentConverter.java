package com.shu.backend.vo.converter;

import com.shu.backend.po.Document;
import com.shu.backend.vo.DocumentVO;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtil.localDatetime2String;

public class DocumentConverter {

    public static DocumentVO convertDocToDocVO(Document source) {
        if (source == null) {
            return null;
        }

        DocumentVO target = new DocumentVO();

        target.setDocId(source.getId());
        target.setCorpusId(source.getCorpusId());
        target.setDocName(source.getName());
        target.setDocType(source.getType());
        target.setDocPath(source.getPath());
        target.setChunkSize(source.getChunkSize());
        target.setVectorSize(source.getVectorState());
        target.setCreateBy(source.getCreateBy());
        target.setCreateTime(localDatetime2String(source.getCreateTime()));
        target.setUpdateBy(source.getUpdateBy());
        target.setUpdateTime(localDatetime2String(source.getUpdateTime()));

        return target;
    }

    public static List<DocumentVO> convertDocListToDocVOList(List<Document> sourceList) {
        if (sourceList.isEmpty()) {
            return null;
        }

        List<DocumentVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertDocToDocVO(source)));

        return targetList;
    }
}
