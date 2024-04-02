package com.shu.backend.vo.converter;

import com.shu.backend.po.Corpus;
import com.shu.backend.vo.CorpusVO;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtil.localDatetime2String;

public class CorpusConverter {

    public static CorpusVO convertCorpusToCorpusVO(Corpus source) {
        if (source == null) {
            return null;
        }

        CorpusVO target = new CorpusVO();

        target.setCorpusId(source.getId());
        target.setCorpusName(source.getName());
        target.setCorpusDesc(source.getCorpusDesc());
        target.setCreateBy(source.getCreateBy());
        target.setCreateTime(localDatetime2String(source.getCreateTime()));
        target.setUpdateBy(source.getUpdateBy());
        target.setUpdateTime(localDatetime2String(source.getUpdateTime()));

        return target;
    }

    public static List<CorpusVO> convertCorpusListToCorpusVOList(List<Corpus> sourceList) {
        if (sourceList.isEmpty()) {
            return null;
        }

        List<CorpusVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertCorpusToCorpusVO(source)));

        return targetList;
    }
}
