package com.shu.backend.vo.converter;

import com.shu.backend.po.Parameter;
import com.shu.backend.vo.ParameterVO;
import com.shu.backend.vo.request.parameter.EditParameterReq;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 16:56
 */
public class ParameterConverter {
    public static ParameterVO converterParameterToParameterVO(Parameter source){
        if(source==null){
            return null;
        }

        ParameterVO target = new ParameterVO();
        target.setUserId(source.getUserId());
        target.setOption(source.getOption());
        target.setMulDialog(source.getMulDialog());
        target.setTemperature(source.getTemperature());
        target.setIsRerank(source.getIsRerank());
        target.setIsStream(source.getIsStream());

        return target;
    }

    public static Parameter converterParameterEditReqToParameter(EditParameterReq source){
        if (source == null){
            return null;
        }

        Parameter target = new Parameter();
        target.setUserId(source.getUserId());
        target.setOption(source.getOption());
        target.setMulDialog(source.getMulDialog());
        target.setTemperature(source.getTemperature());
        target.setIsRerank(source.getIsRerank());
        target.setIsStream(source.getIsStream());

        return target;
    }
}
