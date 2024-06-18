package com.shu.backend.vo.converter;

import com.shu.backend.po.Log;
import com.shu.backend.vo.LogVO;
import com.shu.backend.vo.request.log.PageListLogReq;

import java.util.ArrayList;
import java.util.List;

import static com.shu.backend.utils.DateConverterUtils.localDatetime2String;
/**
 * @author Jaanai（XZY）
 * @date 2024/5/31 20:19
 */
public class LogConverter {
    public static LogVO convertLogToLogVO(Log source){
        if(source==null){
            return null;
        }

        LogVO target = new LogVO();
        target.setId(source.getId());
        target.setEventId(source.getEventId());
        target.setEventDate(localDatetime2String(source.getEventDate()));
        target.setException(source.getException());
        target.setFunction(source.getMethod());
        target.setClass_(source.getClass_());
        target.setLevel(source.getLevel());
        target.setMessage(source.getMessage());
        target.setThread(source.getThread());
        target.setTime(localDatetime2String(source.getTime()));

        return target;
    }

    public static List<LogVO> convertLogListToLogVOList(List<Log> sourceList){
        if(sourceList.isEmpty()){
            return null;
        }

        List<LogVO> targetList = new ArrayList<>();

        sourceList.forEach((source)->targetList.add(convertLogToLogVO(source)));

        return targetList;
    }

    public static Log convertPageListLogReqToLog(PageListLogReq source){
        if(source==null){
            return null;
        }
        Log target = new Log();

        target.setId(source.getId());
        target.setEventId(source.getEventId());
        target.setException(source.getException());
        target.setMethod(source.getFunction());
        target.setClass_(source.getClass_());
        target.setLevel(source.getLevel());
        target.setMessage(source.getMessage());
        target.setThread(source.getThread());

        return target;

    }
}
