package com.shu.backend.vo.converter;

import com.shu.backend.po.EmbedModel;
import com.shu.backend.vo.EmbedModelVO;
import com.shu.backend.vo.request.embedModel.AddEmbedModelReq;
import com.shu.backend.vo.request.embedModel.EditEmbedModelReq;
import com.shu.backend.vo.request.embedModel.PageListEmbedModelReq;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 12:12
 */
public class EmbedModelConverter {

    public static EmbedModelVO converterEmbedModelToEmbedModelVO(EmbedModel resource){
        if(resource==null){
            return null;
        }
        EmbedModelVO target = new EmbedModelVO();

        target.setId(resource.getId());
        target.setModelName(resource.getNickName());
        target.setModelEmbedId(resource.getEmbedId());
        target.setModelDimensions(resource.getDimensions());
        target.setModelMaxTokens(resource.getMaxTokens());
        target.setModelLang(resource.getLang());
        target.setModelId(resource.getModelId());
        target.setModelUrl(resource.getModelUrl());
        target.setModelDescription(resource.getDescription());
        target.setCreatedAt(localDatetime2String(resource.getCreatedAt()));
        target.setUpdatedAt(localDatetime2String(resource.getUpdatedAt()));
        target.setCreateBy(resource.getCreateBy());
        target.setUpdateBy(resource.getUpdateBy());

        return target;
    }

    public static List<EmbedModelVO> converterEmbedModelListToEmbedModelVOList(List<EmbedModel> resourceList){
        if(resourceList.isEmpty()){
            return null;
        }

        List<EmbedModelVO> targetList = new ArrayList<>();
        resourceList.forEach((resource)->targetList.add(converterEmbedModelToEmbedModelVO(resource)));

        return targetList;
    }

    public static EmbedModel converterPageListEmbedModelReqToEmbedModel(PageListEmbedModelReq source){
        if(source==null){
            return null;
        }
        EmbedModel target = new EmbedModel();
        target.setId(source.getId());
        target.setNickName(source.getModelName());
        target.setEmbedId(source.getModelEmbedId());
        target.setDescription(source.getModelDescription());
        target.setMaxTokens(source.getModelMaxTokens());
        target.setLang(source.getModelLang());
        target.setModelId(source.getModelId());
        target.setModelUrl(source.getModelUrl());
        target.setDimensions(source.getModelDimensions());
        target.setCreateBy(source.getCreateBy());
        target.setUpdateBy(source.getUpdateBy());

        return target;

    }

    public static EmbedModel converterAddEmbedModelReqToModel(AddEmbedModelReq source){
        if(source==null){
            return null;
        }
        EmbedModel target = new EmbedModel();
        target.setNickName(source.getModelName());
        target.setModelUrl(source.getModelUrl());
        target.setModelId(source.getModelId());
        target.setDimensions(source.getModelDimensions());
        target.setLang(source.getModelLang());
        target.setMaxTokens(source.getModelMaxTokens());
        target.setDescription(source.getModelDescription());
        return target;
    }

    public static EmbedModel converterEditEmbedModelReqToModel(EditEmbedModelReq source){
        if(source==null){
            return null;
        }
        EmbedModel target = new EmbedModel();
        target.setId(source.getId());
        target.setNickName(source.getModelName());
        target.setModelUrl(source.getModelUrl());
        target.setModelId(source.getModelId());
        target.setDimensions(source.getModelDimensions());
        target.setLang(source.getModelLang());
        target.setMaxTokens(source.getModelMaxTokens());
        target.setDescription(source.getModelDescription());
        return target;
    }

}
