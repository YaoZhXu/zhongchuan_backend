package com.shu.backend.vo.converter;

import com.shu.backend.po.Model;
import com.shu.backend.vo.ModelVO;
import com.shu.backend.vo.request.model.AddModelReq;
import com.shu.backend.vo.request.model.EditModelReq;
import com.shu.backend.vo.request.model.PageListModelReq;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;

public class ModelConverter {

    public static ModelVO convertModelToModelVO(Model source) {
        if (source == null) {
            return null;
        }

        ModelVO target = new ModelVO();

        target.setModelId(source.getId());
        target.setModelUid(source.getUid());
        target.setModelType(source.getType());
        target.setModelName(source.getName());
        target.setModelLang(source.getLang());
        target.setModelAbility(source.getAbility());
        target.setModelDescription(source.getAbility());
        target.setModelFormat(source.getFormat());
        target.setModelSizeInBillions(source.getSizeInBillions());
        target.setQuantization(source.getQuantization());
        target.setRevision(source.getRevision());
        target.setContextLength(source.getContextLength());
        target.setCreateBy(source.getCreateBy());
        target.setCreateTime(localDatetime2String(source.getCreateTime()));
        target.setUpdateBy(source.getUpdateBy());
        target.setUpdateTime(localDatetime2String(source.getUpdateTime()));
        target.setIsRun(source.getIsRun());

        return target;
    }

    public static List<ModelVO> convertModelListToModelVOList(List<Model> sourceList) {
        if (sourceList.isEmpty()) {
            return null;
        }

        List<ModelVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertModelToModelVO(source)));

        return targetList;
    }

    public static Model convertAddModelReqToModel(AddModelReq source) {
        if (source == null) {
            return null;
        }

        Model target = new Model();

        target.setType(source.getModelType());
        target.setName(source.getModelName());
        target.setLang(source.getModelLang());
        target.setAbility(source.getModelAbility());
        target.setModelDesc(source.getModelDescription());
        target.setFormat(source.getModelFormat());
        target.setSizeInBillions(source.getModelSizeInBillions());
        target.setQuantization(source.getQuantization());
        target.setRevision(source.getRevision());
        target.setContextLength(source.getContextLength());
        target.setIsRun("false");

        return target;
    }

    public static Model convertEditModelReqToModel(EditModelReq source) {
        if (source == null) {
            return null;
        }

        Model target = new Model();

        target.setId(source.getModelId());
        target.setType(source.getModelType());
        target.setName(source.getModelName());
        target.setLang(source.getModelLang());
        target.setAbility(source.getModelAbility());
        target.setModelDesc(source.getModelDescription());
        target.setFormat(source.getModelFormat());
        target.setSizeInBillions(source.getModelSizeInBillions());
        target.setQuantization(source.getQuantization());
        target.setRevision(source.getRevision());
        target.setContextLength(source.getContextLength());
        target.setIsRun("false");

        return target;
    }

    public static Model convertPageListModelReqToModel(PageListModelReq source) {
        if (source == null) {
            return null;
        }

        Model target = new Model();

        target.setId(source.getModelId());
        target.setUid(source.getModelUid());
        target.setType(source.getModelType());
        target.setName(source.getModelName());
        target.setLang(source.getModelLang());
        target.setAbility(source.getModelAbility());
        target.setModelDesc(source.getModelDescription());
        target.setFormat(source.getModelFormat());
        target.setSizeInBillions(source.getModelSizeInBillions());
        target.setQuantization(source.getQuantization());
        target.setRevision(source.getRevision());
        target.setContextLength(source.getContextLength());
        target.setCreateBy(source.getCreateBy());
        target.setUpdateBy(source.getUpdateBy());

        return target;
    }
}
