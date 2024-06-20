package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.ParameterMapper;
import com.shu.backend.po.Parameter;
import com.shu.backend.service.ParameterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 17:28
 */
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements ParameterService {

    @Resource
    ParameterMapper parameterMapper;

    @Override
    public Parameter queryParameter(String userId) {
        if (userId==null){
            return null;
        }

        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Parameter::getUserId,userId);
        return parameterMapper.selectOne(wrapper);
    }

    @Override
    public boolean edit(Parameter parameter) {
        if(parameter.getUserId()==null){
            return false;
        }

        LambdaUpdateWrapper<Parameter> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Parameter::getUserId,parameter.getUserId());
        if(StringUtils.isNotBlank(parameter.getOption())){
            wrapper.set(Parameter::getOption,parameter.getOption());
        }
        if(StringUtils.isNotBlank(parameter.getTemperature())){
            wrapper.set(Parameter::getTemperature,parameter.getTemperature());
        }
        if(StringUtils.isNotBlank(parameter.getMulDialog())){
            wrapper.set(Parameter::getMulDialog,parameter.getMulDialog());
        }
        if(StringUtils.isNotBlank(parameter.getIsRerank())){
            wrapper.set(Parameter::getIsRerank,parameter.getIsRerank());
        }
        if(StringUtils.isNotBlank(parameter.getIsStream())){
            wrapper.set(Parameter::getIsStream,parameter.getIsStream());
        }


        return update(wrapper);
    }
}
