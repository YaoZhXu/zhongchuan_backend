package com.shu.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.backend.mapper.LogMapper;
import com.shu.backend.po.Log;
import com.shu.backend.service.LogService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 19:51
 */

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public Page<Log> list(int pageNo, int pageSize, Log log) {
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();
        if(log.getId()!=null){
            wrapper.like(Log::getId,log.getId());
        }
        if(log.getEventId()!=null){
            wrapper.like(Log::getEventId,log.getEventId());
        }
        if(log.getEventDate()!=null){
            wrapper.like(Log::getEventDate,log.getEventDate());
        }
        if(log.getThread()!=null){
            wrapper.like(Log::getThread,log.getThread());
        }
        if(log.getClass_()!=null){
            wrapper.like(Log::getClass_,log.getClass_());
        }
        if(log.getMethod()!=null){
            wrapper.like(Log::getMethod,log.getMethod());
        }
        if(log.getMessage()!=null){
            wrapper.like(Log::getMessage,log.getMessage());
        }
        if(log.getException()!=null){
            wrapper.like(Log::getException,log.getException());
        }
        if(log.getLevel()!=null){
            wrapper.like(Log::getLevel,log.getLevel());
        }
        if(log.getTime()!=null){
            wrapper.like(Log::getTime,log.getTime());
        }
        Page<Log> p = new Page<>(pageNo,pageSize);

        return page(p,wrapper);
    }

    @Override
    public boolean delete(BigInteger logId){
        if(logId==null||getById(logId)==null){
            return false;
        }

        return removeById(logId);
    }
}
