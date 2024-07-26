package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.RerankModelMapper;
import com.shu.backend.po.RerankModel;
import com.shu.backend.service.RerankModelService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:38
 */
@Service
public class RerankModelServiceImpl extends ServiceImpl<RerankModelMapper, RerankModel> implements RerankModelService {

    @Override
    public Page<RerankModel> list(Integer pageNo, Integer pageSize, RerankModel rerankModel) {

        LambdaQueryWrapper<RerankModel> wrapper = new LambdaQueryWrapper<>();
        if(rerankModel.getId()!=null){
            wrapper.eq(RerankModel::getId,rerankModel.getId());
        }
        if(rerankModel.getNickName()!=null){
            wrapper.like(RerankModel::getNickName,rerankModel.getNickName());
        }
        if(rerankModel.getModelId()!=null){
            wrapper.like(RerankModel::getModelId,rerankModel.getModelId());
        }
        if(rerankModel.getModelUrl()!=null){
            wrapper.like(RerankModel::getModelUrl,rerankModel.getModelUrl());
        }
        if(rerankModel.getType()!=null){
            wrapper.like(RerankModel::getType,rerankModel.getType());
        }
        if(rerankModel.getLang()!=null){
            wrapper.like(RerankModel::getLang,rerankModel.getLang());
        }
        if(rerankModel.getDescription()!=null){
            wrapper.like(RerankModel::getDescription,rerankModel.getDescription());
        }

        Page<RerankModel> p = new Page<>(pageNo,pageSize);
        return page(p,wrapper);
    }

    @Override
    public Integer insert(RerankModel rerankModel) {
        if(StringUtils.isBlank(rerankModel.getNickName())){
            return null;
        }
        rerankModel.setCreateBy(UserContextHolder.getUserInfo().getUsername());
        rerankModel.setUpdateBy(UserContextHolder.getUserInfo().getUsername());
        save(rerankModel);
        return rerankModel.getId();
    }

    @Override
    public boolean update(RerankModel rerankModel) {
        if(rerankModel.getId()==null||getById(rerankModel.getId())==null){
            return false;
        }
        LambdaUpdateWrapper<RerankModel> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(RerankModel::getId,rerankModel.getId());
        if(StringUtils.isNotBlank(rerankModel.getNickName())){
            wrapper.set(RerankModel::getNickName,rerankModel.getNickName());
        }
        if(StringUtils.isNotBlank(rerankModel.getModelId())){
            wrapper.set(RerankModel::getModelId,rerankModel.getModelId());
        }
        if(StringUtils.isNotBlank(rerankModel.getModelUrl())){
            wrapper.set(RerankModel::getModelUrl,rerankModel.getModelUrl());
        }
        if(StringUtils.isNotBlank(rerankModel.getType())){
            wrapper.set(RerankModel::getType,rerankModel.getType());
        }
        if(StringUtils.isNotBlank(rerankModel.getLang())){
            wrapper.set(RerankModel::getLang,rerankModel.getLang());
        }
        if(StringUtils.isNotBlank(rerankModel.getDescription())){
            wrapper.set(RerankModel::getDescription,rerankModel.getDescription());
        }

        wrapper.set(RerankModel::getUpdateBy,UserContextHolder.getUserInfo().getUsername());
        return update(wrapper);

    }

    @Override
    public boolean delete(Integer id) {
        if(id==null || getById(id)==null){
            return false;
        }
        return removeById(id);
    }
}
