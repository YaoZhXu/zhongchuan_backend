package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.EmbedModelMapper;
import com.shu.backend.po.EmbedModel;
import com.shu.backend.service.EmbedModelService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 14:31
 */
@Service
public class EmbedModelServiceImpl extends ServiceImpl<EmbedModelMapper,EmbedModel> implements EmbedModelService {

    @Override
    public Page<EmbedModel> list(Integer pageNo, Integer pageSize, EmbedModel embedModel) {

        LambdaQueryWrapper<EmbedModel> wrapper = new LambdaQueryWrapper<>();
        if(embedModel.getId()!=null){
            wrapper.eq(EmbedModel::getId,embedModel.getId());
        }
        if(embedModel.getEmbedId()!=null){
            wrapper.like(EmbedModel::getEmbedId,embedModel.getEmbedId());
        }
        if(embedModel.getDimensions()!=null){
            wrapper.like(EmbedModel::getDimensions,embedModel.getDimensions());
        }
        if(embedModel.getDescription()!=null){
            wrapper.like(EmbedModel::getDescription,embedModel.getDescription());
        }
        if(embedModel.getMaxTokens()!=null){
            wrapper.like(EmbedModel::getMaxTokens,embedModel.getMaxTokens());
        }
        if(embedModel.getModelId()!=null){
            wrapper.like(EmbedModel::getModelId,embedModel.getModelId());
        }
        if(embedModel.getModelUrl()!=null){
            wrapper.like(EmbedModel::getModelUrl,embedModel.getModelUrl());
        }
        if(embedModel.getNickName()!=null){
            wrapper.like(EmbedModel::getNickName,embedModel.getNickName());
        }

        Page<EmbedModel> p = new Page<>(pageNo,pageSize);
        return page(p,wrapper);
    }

    @Override
    public Integer insert(EmbedModel embedModel) {
        if(StringUtils.isBlank(embedModel.getNickName())){
            return null;
        }
//        System.out.println("embedModel = " + embedModel.toString());

        embedModel.setCreateBy(UserContextHolder.getUserInfo().getUsername());
        embedModel.setUpdateBy(UserContextHolder.getUserInfo().getUsername());
        save(embedModel);
        return embedModel.getId();
    }

    @Override
    public boolean update(EmbedModel embedModel) {
        if(embedModel.getId()==null||getById(embedModel.getId())==null){
            return false;
        }

        LambdaUpdateWrapper<EmbedModel> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(EmbedModel::getId,embedModel.getId());
        if(StringUtils.isNotBlank(embedModel.getNickName())){
            wrapper.set(EmbedModel::getNickName,embedModel.getNickName());
        }
        if(StringUtils.isNotBlank(embedModel.getModelId())){
            wrapper.set(EmbedModel::getModelId,embedModel.getModelId());
        }
        if(StringUtils.isNotBlank(embedModel.getModelUrl())){
            wrapper.set(EmbedModel::getModelUrl,embedModel.getModelUrl());
        }
        if(StringUtils.isNotBlank(embedModel.getLang())){
            wrapper.set(EmbedModel::getLang,embedModel.getLang());
        }
        if(StringUtils.isNotBlank(embedModel.getDimensions())){
            wrapper.set(EmbedModel::getDimensions,embedModel.getDimensions());
        }
        if(StringUtils.isNotBlank(embedModel.getMaxTokens())){
            wrapper.set(EmbedModel::getMaxTokens,embedModel.getMaxTokens());
        }
        if(StringUtils.isNotBlank(embedModel.getDescription())){
            wrapper.set(EmbedModel::getDescription,embedModel.getDescription());
        }

        wrapper.set(EmbedModel::getUpdateBy,UserContextHolder.getUserInfo().getUsername());
        return update(wrapper);

    }

    @Override
    public boolean delete(Integer id) {
        if(id==null||getById(id)==null){
            return false;
        }
        return removeById(id);
    }
}
