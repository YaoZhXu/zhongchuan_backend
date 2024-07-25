package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Document;
import com.shu.backend.service.CorpusService;
import com.shu.backend.service.DocumentService;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.vo.DocumentVO;
import com.shu.backend.vo.ListAllDocVO;
import com.shu.backend.vo.request.doc.AddDocReq;
import com.shu.backend.vo.request.doc.DeleteDocReq;
import com.shu.backend.vo.request.doc.PageListAllDocReq;
import com.shu.backend.vo.request.doc.PageListDocReq;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import com.shu.backend.vo.response.doc.AddDocResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.Collectors;

import static com.shu.backend.constants.DocEntryType.DOCTYPE2STRING;
import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;
import static com.shu.backend.vo.converter.DocumentConverter.convertDocListToDocVOList;

@Slf4j
@RestController
@RequestMapping("/api/doc")
public class DocumentController {

    @Resource
    private CorpusService corpusService;

    @Resource
    private DocumentService documentService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListDocReq req) {
        Page<Document> result = documentService.list(req.getPageNo(), req.getPageSize(), req.getCorpusId(), null);

        PageInfo<DocumentVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertDocListToDocVOList(result.getRecords()));

        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/listAll")
    public CommonPageResponse listAll(@Validated @RequestBody PageListAllDocReq req) {
        Page<Document> result = documentService.list(req.getPageNo(), req.getPageSize(), null, req.getDocName());

        PageInfo<ListAllDocVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);

        List<Document> records = result.getRecords();
        List<ListAllDocVO> collect = records.stream().map((record) -> {
            ListAllDocVO listAllDocVO = new ListAllDocVO();
            listAllDocVO.setDocId(record.getId());
            listAllDocVO.setDocName(record.getName());
            listAllDocVO.setDocType(DOCTYPE2STRING.get(record.getType()));
            listAllDocVO.setCorpusId(record.getCorpusId());
            listAllDocVO.setCorpusName(corpusService.queryById(record.getCorpusId()).getName());
            listAllDocVO.setCreateBy(record.getCreateBy());
            listAllDocVO.setCreateTime(localDatetime2String(record.getCreateTime()));
            listAllDocVO.setUpdateBy(record.getUpdateBy());
            listAllDocVO.setUpdateTime(localDatetime2String(record.getUpdateTime()));
            return listAllDocVO;
        }).collect(Collectors.toList());
        pageInfo.setRecords(collect);

        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddDocReq req) {
        Long result = documentService.add(req.getCorpusId(), req.getDocName(), req.getPath());
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新建文档失败");
        }

        AddDocResp resp = new AddDocResp();
        resp.setDocId(result);
//        log.info("user '"+ UserContextHolder.getUserInfo().getUserId()+"' add a document.");
        return CommonResponse.success(resp);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteDocReq req) {
        boolean result = documentService.delete(req.getDocId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除文档失败");
        }
//        log.warn("user '"+ UserContextHolder.getUserInfo().getUserId()+"' delete a document.");
        return CommonResponse.success();
    }
}
