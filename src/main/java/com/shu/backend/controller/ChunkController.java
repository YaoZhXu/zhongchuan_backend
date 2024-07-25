package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Chunk;
import com.shu.backend.service.ChunkService;
import com.shu.backend.utils.UserContextHolder;
import com.shu.backend.vo.ChunkVO;
import com.shu.backend.vo.request.chunk.*;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import com.shu.backend.vo.response.chunk.AddChunkResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.ChunkConverter.convertChunkListToChunkVOList;
import static com.shu.backend.vo.converter.ChunkConverter.convertChunkToChunkVO;

@Slf4j
@RestController
@RequestMapping("/api/chunk")
public class ChunkController {

    @Resource
    private ChunkService chunkService;

    @PostMapping("/list")
    public CommonPageResponse list(@Validated @RequestBody PageListChunkReq req) {
        Page<Chunk> result = chunkService.list(req.getPageNo(), req.getPageSize(), req.getDocId());

        PageInfo<ChunkVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertChunkListToChunkVOList(result.getRecords()));

        return CommonPageResponse.success(pageInfo);
    }

    @PostMapping("/add")
    public CommonResponse add(@Validated @RequestBody AddChunkReq req) {
        Long result = chunkService.add(req.getCorpusId(), req.getDocId(), req.getTitle(), req.getContent(),
                req.getPagination(), req.getImageUrl());
        if (result == null) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新建分片失败");
        }

        AddChunkResp resp = new AddChunkResp();
        resp.setChunkId(result);

        return CommonResponse.success(resp);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@Validated @RequestBody EditChunkReq req) {
        boolean result = chunkService.edit(req.getChunkId(), req.getContent());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "编辑分片失败");
        }

        return CommonResponse.success();
    }

    @GetMapping("/queryById")
    public CommonResponse queryByChunkId(@Validated @RequestBody QueryByChunkIdReq req) {
        Chunk chunk = chunkService.queryById(req.getChunkId());
        return CommonResponse.success(convertChunkToChunkVO(chunk));
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteChunkReq req) {
        boolean result = chunkService.delete(req.getChunkId());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除分片失败");
        }

        return CommonResponse.success();
    }
}
