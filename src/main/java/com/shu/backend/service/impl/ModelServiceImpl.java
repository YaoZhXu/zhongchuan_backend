package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.ModelMapper;
import com.shu.backend.po.Model;
import com.shu.backend.service.ModelService;
import com.shu.backend.utils.UserContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    @Override
    public Long insert(Model model) {
        if (StringUtils.isBlank(model.getType()) || StringUtils.isBlank(model.getName()) ||
                StringUtils.isBlank(model.getLang()) || StringUtils.isBlank(model.getAbility()) ||
                StringUtils.isBlank(model.getFormat()) || model.getSizeInBillions() == null ||
                model.getContextLength() == null) {
            return null;
        }

        // TODO 调算法改uid
        model.setUid("等算法");
        model.setCreateBy(UserContextHolder.getUserInfo().getUsername());
        model.setUpdateBy(UserContextHolder.getUserInfo().getUsername());
        save(model);
        return model.getId();
    }

    @Override
    public boolean edit(Model model) {
        if (model.getId() == null || getById(model.getId()) == null) {
            return false;
        }

        LambdaUpdateWrapper<Model> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Model::getId, model.getId());
        if (StringUtils.isNotBlank(model.getType())) {
            wrapper.set(Model::getType, model.getType());
        }
        if (StringUtils.isNotBlank(model.getName())) {
            wrapper.set(Model::getName, model.getName());
        }
        if (StringUtils.isNotBlank(model.getLang())) {
            wrapper.set(Model::getLang, model.getLang());
        }
        if (StringUtils.isNotBlank(model.getAbility())) {
            wrapper.set(Model::getAbility, model.getAbility());
        }
        if (StringUtils.isNotBlank(model.getModelDesc())) {
            wrapper.set(Model::getModelDesc, model.getModelDesc());
        }
        if (StringUtils.isNotBlank(model.getFormat())) {
            wrapper.set(Model::getFormat, model.getFormat());
        }
        if (model.getSizeInBillions() != null) {
            wrapper.set(Model::getSizeInBillions, model.getSizeInBillions());
        }
        if (StringUtils.isNotBlank(model.getQuantization())) {
            wrapper.set(Model::getQuantization, model.getQuantization());
        }
        if (StringUtils.isNotBlank(model.getRevision())) {
            wrapper.set(Model::getRevision, model.getRevision());
        }
        if (model.getContextLength() != null) {
            wrapper.set(Model::getContextLength, model.getContextLength());
        }

        // TODO 调算法改uid
        wrapper.set(Model::getUid, "等算法");
        wrapper.set(Model::getUpdateBy, UserContextHolder.getUserInfo().getUsername());
        return update(wrapper);
    }

    @Override
    public boolean delete(Long modelId) {
        if (modelId == null || getById(modelId) == null) {
            return false;
        }

        // TODO 调算法
        return removeById(modelId);
    }

    @Override
    public Model queryById(Long modelId) {
        if (modelId == null) {
            return null;
        }

        return getById(modelId);
    }

    @Override
    public Page<Model> list(int pageNo, int pageSize, Model model) {
        LambdaQueryWrapper<Model> wrapper = new LambdaQueryWrapper<>();
        if (model.getId() != null) {
            wrapper.eq(Model::getId, model.getId());
        }
        if (model.getUid() != null) {
            wrapper.like(Model::getUid, model.getUid());
        }
        if (model.getType() != null) {
            wrapper.like(Model::getType, model.getType());
        }
        if (model.getName() != null) {
            wrapper.like(Model::getName, model.getName());
        }
        if (model.getLang() != null) {
            wrapper.like(Model::getLang, model.getLang());
        }
        if (model.getAbility() != null) {
            wrapper.like(Model::getAbility, model.getAbility());
        }
        if (model.getModelDesc() != null) {
            wrapper.like(Model::getModelDesc, model.getModelDesc());
        }
        if (model.getFormat() != null) {
            wrapper.like(Model::getFormat, model.getFormat());
        }
        if (model.getSizeInBillions() != null) {
            wrapper.eq(Model::getSizeInBillions, model.getSizeInBillions());
        }
        if (model.getQuantization() != null) {
            wrapper.like(Model::getQuantization, model.getQuantization());
        }
        if (model.getRevision() != null) {
            wrapper.like(Model::getRevision, model.getRevision());
        }
        if (model.getContextLength() != null) {
            wrapper.eq(Model::getContextLength, model.getContextLength());
        }
        if(model.getIsRun() != null){
            wrapper.eq(Model::getIsRun, model.getIsRun());
        }

        Page<Model> p = new Page<>(pageNo, pageSize);
        return page(p, wrapper);
    }
}
