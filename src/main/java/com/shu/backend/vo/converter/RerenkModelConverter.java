package com.shu.backend.vo.converter;

import com.shu.backend.po.RerankModel;
import com.shu.backend.vo.RerankModelVO;
import com.shu.backend.vo.request.rerankModel.AddRerankModelReq;
import com.shu.backend.vo.request.rerankModel.EditRerankModelReq;
import com.shu.backend.vo.request.rerankModel.PageListRerankModelReq;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:28
 */
public class RerenkModelConverter {

    public static RerankModelVO converterRerankModelToRerankModelVO(RerankModel source){
        if(source==null){
            return null;
        }
        RerankModelVO target = new RerankModelVO();
        target.setId(source.getId());
        target.setModelName(source.getNickName());
        target.setModelId(source.getModelId());
        target.setModelUrl(source.getModelUrl());
        target.setModelLang(source.getLang());
        target.setModelType(source.getType());
        target.setModelDescription(source.getDescription());
        target.setCreateBy(source.getCreateBy());
        target.setUpdateBy(source.getUpdateBy());
        target.setCreateTime(localDatetime2String(source.getCreatedAt()));
        target.setUpdateTime(localDatetime2String(source.getUpdatedAt()));

        return target;
    }

    public static List<RerankModelVO> converterRerankModelListToRerankModelVOList(List<RerankModel> sourceList){
        if(sourceList.isEmpty()){
            return null;
        }

        List<RerankModelVO> target = new ArrayList<>();
        sourceList.forEach((source)->target.add(converterRerankModelToRerankModelVO(source)));
        return target;
    }

    public static RerankModel converterPageListRerankModelReqToRerankModel(PageListRerankModelReq source){
        if(source==null){
            return null;
        }

        RerankModel target = new RerankModel();
        target.setId(source.getId());
        target.setNickName(source.getModelName());
        target.setModelId(source.getModelId());
        target.setModelUrl(source.getModelUrl());
        target.setLang(source.getModelLang());
        target.setType(source.getModelType());
        target.setDescription(source.getModelDescription());
        target.setCreateBy(source.getCreateBy());
        target.setUpdateBy(source.getUpdateBy());

        return target;

    }

    public static RerankModel converterAddRerankModelReqToRerankModel(AddRerankModelReq source){
        if(source==null){
            return null;
        }
        RerankModel target = new RerankModel();
        target.setNickName(source.getModelName());
        target.setModelId(source.getModelId());
        target.setModelUrl(source.getModelUrl());
        target.setType(source.getModelType());
        target.setLang(source.getModelLang());
        target.setDescription(source.getModelDescription());
        return target;
    }

    public static RerankModel converterEditRerankModelReqToRerankModel(EditRerankModelReq source){
        if(source==null){
            return null;
        }
        RerankModel target = new RerankModel();
        target.setId(source.getId());
        target.setNickName(source.getModelName());
        target.setModelId(source.getModelId());
        target.setModelUrl(source.getModelUrl());
        target.setLang(source.getModelLang());
        target.setType(source.getModelType());
        target.setDescription(source.getModelDescription());
        return target;
    }
}
