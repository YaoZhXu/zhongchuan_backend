package com.shu.backend.vo.converter;

import com.shu.backend.po.Model;
import com.shu.backend.vo.ModelVO;
import com.shu.backend.vo.request.AddModelReq;
import com.shu.backend.vo.request.EditModelReq;
import com.shu.backend.vo.request.PageListModelReq;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtil.localDatetime2String;

public class ModelConverter {

    public static ModelVO convertModelToModelVO(Model source) {
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

        return target;
    }

    public static List<ModelVO> convertModelListToModelVOList(List<Model> sourceList) {
        List<ModelVO> targetList = new ArrayList<>();

        sourceList.forEach((source) -> targetList.add(convertModelToModelVO(source)));

        return targetList;
    }

    public static Model convertAddModelReqToModel(AddModelReq source) {
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

        return target;
    }

    public static Model convertEditModelReqToModel(EditModelReq source) {
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

        return target;
    }

    public static Model convertPageListModelReqToModel(PageListModelReq source) {
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
