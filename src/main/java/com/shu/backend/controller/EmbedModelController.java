package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.EmbedModel;
import com.shu.backend.service.EmbedModelService;
import com.shu.backend.vo.EmbedModelVO;
import com.shu.backend.vo.request.embedModel.AddEmbedModelReq;
import com.shu.backend.vo.request.embedModel.DeleteEmbedModelReq;
import com.shu.backend.vo.request.embedModel.EditEmbedModelReq;
import com.shu.backend.vo.request.embedModel.PageListEmbedModelReq;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import static com.shu.backend.vo.converter.EmbedModelConverter.*;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 14:27
 */
@RestController
@RequestMapping("/api/embedModel")
public class EmbedModelController {

    @Resource
    private EmbedModelService embedModelService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListEmbedModelReq req){
        Page<EmbedModel> result = embedModelService.list(req.getPageNo(),req.getPageSize(),converterPageListEmbedModelReqToEmbedModel(req));

        PageInfo<EmbedModelVO> info = new PageInfo<>();
        info.fill(result);
        info.setRecords(converterEmbedModelListToEmbedModelVOList(result.getRecords()));
        return CommonPageResponse.success(info);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddEmbedModelReq req){
        Integer result = embedModelService.insert(converterAddEmbedModelReqToModel(req));
        if(result==null){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),"添加模型失败");
        }
        return CommonResponse.success(result);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@Validated @RequestBody EditEmbedModelReq req){
        boolean result = embedModelService.update(converterEditEmbedModelReqToModel(req));
        if(!result){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "模型修改失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteEmbedModelReq req){
        boolean result = embedModelService.delete(req.getId());
        if(!result){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "模型删除失败");
        }
        return CommonResponse.success();
    }


}
