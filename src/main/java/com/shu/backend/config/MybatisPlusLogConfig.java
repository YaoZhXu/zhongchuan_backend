package com.shu.backend.config;

import org.apache.ibatis.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jaanai（XZY）
 * @date 2024/6/24 15:46
 */
public class MybatisPlusLogConfig implements Log {

    private Logger log;
    public MybatisPlusLogConfig(String clazz){
        log = LoggerFactory.getLogger("SQL");
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s,e);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        log.info(s);// debug输出至info日志层级
    }

    @Override
    public void trace(String s) {
        log.info(s); // trace输出至info日志层级
    }

    @Override
    public void warn(String s) {
        log.info(s); // warn输出至info日志层级
    }
}
