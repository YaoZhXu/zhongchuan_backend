package com.shu.backend.controller;

import com.shu.backend.po.Parameter;
import com.shu.backend.service.ParameterService;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.vo.request.parameter.EditParameterReq;
import com.shu.backend.vo.request.parameter.QueryParameterReq;
import com.shu.backend.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.ParameterConverter.*;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/20 17:47
 */
@Slf4j
@RestController
@RequestMapping("/api/parameter")
public class ParameterController {
    @Resource
    private ParameterService parameterService;

    @PostMapping("/query")
    public CommonResponse queryParameter(@Validated @RequestBody QueryParameterReq req){
        Parameter parameter = parameterService.queryParameter(req.getUserId());
        return CommonResponse.success(converterParameterToParameterVO(parameter));
    }

    @PostMapping("/edit")
    public CommonResponse editParameter(@Validated @RequestBody EditParameterReq req){
        boolean result = parameterService.edit(converterParameterEditReqToParameter(req));
        if(!result){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "编辑参数失败");
        }

        return CommonResponse.success();
    }
}
