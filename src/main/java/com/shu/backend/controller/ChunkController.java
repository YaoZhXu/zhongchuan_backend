package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Chunk;
import com.shu.backend.service.ChunkService;
import com.shu.backend.vo.ChunkVO;
import com.shu.backend.vo.request.*;
import com.shu.backend.vo.response.CommonPageResponse;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.ChunkConverter.convertChunkListToChunkVOList;
import static com.shu.backend.vo.converter.ChunkConverter.convertChunkToChunkVO;

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
