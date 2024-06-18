package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Model;
import com.shu.backend.service.ModelService;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.vo.ModelVO;
import com.shu.backend.vo.request.model.*;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.ModelConverter.*;

@Slf4j
@RestController
@RequestMapping("/api/model")
public class ModelController {

    @Resource
    private ModelService modelService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListModelReq req) {
        Page<Model> result = modelService.list(req.getPageNo(), req.getPageSize(), convertPageListModelReqToModel(req));

        PageInfo<ModelVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertModelListToModelVOList(result.getRecords()));
        log.info("user '"+ UserContextHolder.getUserInfo().getUserId()+"' check the model list.");
        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddModelReq req) {
        Long result = modelService.insert(convertAddModelReqToModel(req));

        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加模型失败");
        }
        log.info("user '"+ UserContextHolder.getUserInfo().getUserId()+"' add a model.");
        return CommonResponse.success();
    }

    @PostMapping("/edit")
    public CommonResponse edit(@Validated @RequestBody EditModelReq req) {
        boolean result = modelService.edit(convertEditModelReqToModel(req));

        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "编辑模型失败");
        }
        log.warn("user '"+ UserContextHolder.getUserInfo().getUserId()+"' edit a model:"+req.getModelId()+".");
        return CommonResponse.success();
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteModelReq req) {
        boolean result = modelService.delete(req.getModelId());

        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除模型失败");
        }
        log.warn("user '"+ UserContextHolder.getUserInfo().getUserId()+"' delete a model.");
        return CommonResponse.success();
    }

    @GetMapping("/queryById")
    public CommonResponse queryByModelId(@Validated @RequestBody QueryByModelIdReq req) {
        Model model = modelService.queryById(req.getModelId());
        return CommonResponse.success(convertModelToModelVO(model));
    }
}
