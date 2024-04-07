package com.shu.backend.controller;

import com.shu.backend.service.VectorDBService;
import com.shu.backend.vo.request.vector.*;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.vector.CollectionListResp;
import com.shu.backend.vo.response.vector.PartitionListResp;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/vector")
public class VectorController {

    @Resource
    private VectorDBService vectorDBService;

    @PostMapping("/collection/add")
    public CommonResponse createCollection(@Validated @RequestBody CreateCollectionReq req) {
        boolean result = vectorDBService.createCollection(req.getCollectionName());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新建向量库失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/collection/delete")
    public CommonResponse dropCollection(@Validated @RequestBody DropCollectionReq req) {
        boolean result = vectorDBService.dropCollection(req.getCollectionName());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除向量库失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/collection/list")
    public CommonResponse showCollections() {
        List<String> result = vectorDBService.showCollections();

        CollectionListResp resp = new CollectionListResp();
        resp.setCollectionList(result);
        return CommonResponse.success(resp);
    }

    @PostMapping("/collection/has")
    public CommonResponse hasCollection(@Validated @RequestBody HasCollectionReq req) {
        boolean result = vectorDBService.hasCollection(req.getCollectionName());
        return CommonResponse.success(result);
    }

    @PostMapping("/partition/add")
    public CommonResponse createPartition(@Validated @RequestBody CreatePartitionReq req) {
        boolean result = vectorDBService.createPartition(req.getCollectionName(), req.getPartitionName());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新建分区失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/partition/delete")
    public CommonResponse dropPartition(@Validated @RequestBody DropPartitionReq req) {
        boolean result = vectorDBService.dropPartition(req.getCollectionName(), req.getPartitionName());
        if (!result) {
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除分区失败");
        }
        return CommonResponse.success();
    }

    @PostMapping("/partition/list")
    public CommonResponse showPartitions(@Validated @RequestBody PartitionListReq req) {
        List<String> result = vectorDBService.showPartitions(req.getCollectionName());
        PartitionListResp resp = new PartitionListResp();
        resp.setPartitionList(result);
        return CommonResponse.success(resp);
    }

    @PostMapping("/partition/has")
    public CommonResponse hasPartition(@Validated @RequestBody HasPartitionReq req) {
        boolean result = vectorDBService.hasPartition(req.getCollectionName(), req.getPartitionName());
        return CommonResponse.success(result);
    }
}
