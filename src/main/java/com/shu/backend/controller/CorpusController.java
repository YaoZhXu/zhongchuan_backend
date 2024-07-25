package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Corpus;
import com.shu.backend.service.CorpusService;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.vo.CorpusVO;
import com.shu.backend.vo.request.*;
import com.shu.backend.vo.request.corpus.AddCorpusReq;
import com.shu.backend.vo.request.corpus.DeleteCorpusReq;
import com.shu.backend.vo.request.corpus.EditCorpusReq;
import com.shu.backend.vo.request.corpus.QueryByCorpusIdReq;
import com.shu.backend.vo.response.corpus.AddCorpusResp;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.CorpusConverter.convertCorpusListToCorpusVOList;
import static com.shu.backend.vo.converter.CorpusConverter.convertCorpusToCorpusVO;

@Slf4j
@RestController
@RequestMapping("/api/corpus")
public class CorpusController {

    @Resource
    private CorpusService corpusService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageCommonReq req) {
        Page<Corpus> result = corpusService.list(req.getPageNo(), req.getPageSize());

        PageInfo<CorpusVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertCorpusListToCorpusVOList(result.getRecords()));

        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddCorpusReq req) {
        Long result = corpusService.insert(req.getCorpusName(), req.getCorpusDesc());
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新建语料库失败");
        }
        AddCorpusResp resp = new AddCorpusResp();
        resp.setCorpusId(result);

        return CommonResponse.success(resp);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@Validated @RequestBody EditCorpusReq req) {
        boolean result = corpusService.edit(req.getCorpusId(), req.getCorpusName(), req.getCorpusDesc());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "编辑语料库失败");
        }

        return CommonResponse.success();
    }

    @GetMapping("/queryById")
    public CommonResponse queryById(@Validated @RequestBody QueryByCorpusIdReq req) {
        Corpus corpus = corpusService.queryById(req.getCorpusId());
        if (corpus == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询语料库失败");
        }
        return CommonResponse.success(convertCorpusToCorpusVO(corpus));
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteCorpusReq req) {
        boolean result = corpusService.delete(req.getCorpusId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除语料库失败");
        }

        return CommonResponse.success();
    }
}
