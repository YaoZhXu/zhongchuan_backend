package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.RerankModel;
import com.shu.backend.service.RerankModelService;
import com.shu.backend.vo.RerankModelVO;
import com.shu.backend.vo.request.embedModel.DeleteEmbedModelReq;
import com.shu.backend.vo.request.rerankModel.AddRerankModelReq;
import com.shu.backend.vo.request.rerankModel.EditRerankModelReq;
import com.shu.backend.vo.request.rerankModel.PageListRerankModelReq;
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

import static com.shu.backend.vo.converter.RerenkModelConverter.*;

/**
 * @author Jaanai（XZY）
 * @date 2024/7/26 20:39
 */
@RestController
@RequestMapping("/api/rerankModel")
public class RerankModelController {

    @Resource
    private RerankModelService rerankModelService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListRerankModelReq req){

        Page<RerankModel> result = rerankModelService.list(req.getPageNo(),req.getPageSize(),converterPageListRerankModelReqToRerankModel(req));

        PageInfo<RerankModelVO> info = new PageInfo<>();
        info.fill(result);
        info.setRecords(converterRerankModelListToRerankModelVOList(result.getRecords()));

        return CommonPageResponse.success(info);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddRerankModelReq req){
        Integer result = rerankModelService.insert(converterAddRerankModelReqToRerankModel(req));
        if(result==null){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "创建模型失败");
        }
        return CommonResponse.success(result);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@Validated @RequestBody EditRerankModelReq req){
        boolean result = rerankModelService.update(converterEditRerankModelReqToRerankModel(req));
        if(!result){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "修改模型失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteEmbedModelReq req){
        boolean result = rerankModelService.delete(req.getId());
        if(!result){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "模型删除失败");
        }
        return CommonResponse.success();
    }
}
