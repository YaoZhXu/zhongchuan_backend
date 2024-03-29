package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Document;
import com.shu.backend.service.DocumentService;
import com.shu.backend.vo.DocumentVO;
import com.shu.backend.vo.request.DeleteDocReq;
import com.shu.backend.vo.request.PageListDocReq;
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

import static com.shu.backend.vo.converter.DocumentConverter.convertDocListToDocVOList;

@RestController
@RequestMapping("/api/doc")
public class DocumentController {

    @Resource
    private DocumentService documentService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListDocReq req) {
        Page<Document> result = documentService.list(req.getPageNo(), req.getPageSize(), req.getCorpusId());

        PageInfo<DocumentVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertDocListToDocVOList(result.getRecords()));
        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteDocReq req) {
        boolean result = documentService.delete(req.getDocId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除文档失败");
        }
        return CommonResponse.success();
    }
}
