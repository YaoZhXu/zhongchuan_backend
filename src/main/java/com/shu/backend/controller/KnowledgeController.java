package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Knowledge;
import com.shu.backend.service.KnowledgeService;
import com.shu.backend.vo.KnowledgeVO;
import com.shu.backend.vo.request.knowledge.*;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.KnowledgeConverter.*;

@RestController
@RequestMapping("/api/knowledge")
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListKnowledgeReq req) {
        Page<Knowledge> result = knowledgeService.list(req.getPageNo(), req.getPageSize(), convertPageListKnReqToKn(req));

        PageInfo<KnowledgeVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertKnListToKnVOList(result.getRecords()));
        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddKnowledgeReq req) {
        Long result = knowledgeService.insert(convertAddKnReqToKn(req));

        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加向量库失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/edit")
    public CommonResponse edit(@Validated @RequestBody EditKnowledgeReq req) {
        boolean result = knowledgeService.edit(convertEditKnReqToKn(req));

        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "编辑向量库失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteKnowledgeReq req) {
        boolean result = knowledgeService.delete(req.getKnowledgeId());

        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除向量库失败");
        }
        return CommonResponse.success();
    }

    @GetMapping("/queryById")
    public CommonResponse queryByKnowledgeId(@Validated @RequestBody QueryByKnowledgeIdReq req) {
        Knowledge knowledge = knowledgeService.queryById(req.getKnowledgeId());
        return CommonResponse.success(convertKnToKnVO(knowledge));
    }
}
