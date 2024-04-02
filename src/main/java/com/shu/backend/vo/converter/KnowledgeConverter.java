package com.shu.backend.vo.converter;

import com.shu.backend.po.Knowledge;
import com.shu.backend.vo.KnowledgeVO;
import com.shu.backend.vo.request.AddKnowledgeReq;
import com.shu.backend.vo.request.EditKnowledgeReq;
import com.shu.backend.vo.request.PageListKnowledgeReq;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtil.localDatetime2String;

public class KnowledgeConverter {

    public static KnowledgeVO convertKnToKnVO(Knowledge source) {
        if (source == null) {
            return null;
        }

        KnowledgeVO target = new KnowledgeVO();

        target.setKnowledgeId(source.getId());
        target.setKnowledgeName(source.getName());
        target.setKnowledgeInfo(source.getInfo());
        target.setVsType(source.getVsType());
        target.setEmbedModel(source.getEmbedModel());
        target.setFileCount(source.getFileCount());
        target.setCorpusId(source.getCorpusId());
        target.setCreateBy(source.getCreateBy());
        target.setCreateTime(localDatetime2String(source.getCreateTime()));
        target.setUpdateBy(source.getUpdateBy());
        target.setUpdateTime(localDatetime2String(source.getUpdateTime()));

        return target;
    }

    public static List<KnowledgeVO> convertKnListToKnVOList(List<Knowledge> sourceList) {
        if (sourceList.isEmpty()) {
            return null;
        }

        List<KnowledgeVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertKnToKnVO(source)));

        return targetList;
    }

    public static Knowledge convertAddKnReqToKn(AddKnowledgeReq source) {
        if (source == null) {
            return null;
        }

        Knowledge target = new Knowledge();

        target.setName(source.getKnowledgeName());
        target.setInfo(source.getKnowledgeInfo());
        target.setVsType(source.getVsType());
        target.setEmbedModel(source.getEmbedModel());
        target.setCorpusId(source.getCorpusId());

        return target;
    }

    public static Knowledge convertEditKnReqToKn(EditKnowledgeReq source) {
        if (source == null) {
            return null;
        }

        Knowledge target = new Knowledge();

        target.setId(source.getKnowledgeId());
        target.setName(source.getKnowledgeName());
        target.setInfo(source.getKnowledgeInfo());
        target.setVsType(source.getVsType());
        target.setEmbedModel(source.getEmbedModel());
        target.setCorpusId(source.getCorpusId());

        return target;
    }

    public static Knowledge convertPageListKnReqToKn(PageListKnowledgeReq source) {
        if (source == null) {
            return null;
        }

        Knowledge target = new Knowledge();

        target.setId(source.getKnowledgeId());
        target.setName(source.getKnowledgeName());
        target.setInfo(source.getKnowledgeInfo());
        target.setVsType(source.getVsType());
        target.setEmbedModel(source.getEmbedModel());
        target.setFileCount(source.getFileCount());
        target.setCorpusId(source.getCorpusId());

        return target;
    }
}
