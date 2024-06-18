package com.shu.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shu.backend.po.Log;
import com.shu.backend.service.LogService;
import com.shu.backend.vo.LogVO;
import com.shu.backend.vo.request.log.DeleteLogReq;
import com.shu.backend.vo.request.log.PageListLogReq;
import com.shu.backend.vo.response.CommonResponse;
import com.shu.backend.vo.response.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.shu.backend.vo.converter.LogConverter.*;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 19:34
 */
@RestController
@RequestMapping("/api/log")
public class LogController {
    @Resource
    private LogService logService;

    @PostMapping("/list")
    public CommonResponse list(@Validated @RequestBody PageListLogReq req){
        Page<Log> result = logService.list(req.getPageNo(),req.getPageSize(),convertPageListLogReqToLog(req));

        PageInfo<LogVO> pageInfo = new PageInfo<>();
        pageInfo.fill(result);
        pageInfo.setRecords(convertLogListToLogVOList(result.getRecords()));
        return CommonResponse.success(pageInfo);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@Validated @RequestBody DeleteLogReq req){
        boolean result = logService.delete(req.getLogId());

        if(!result){
            return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除模型失败");
        }

        return CommonResponse.success();
    }

}
