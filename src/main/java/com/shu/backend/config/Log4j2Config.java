package com.shu.backend.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Log4j2 ConnectionFactory
 *
 * @author fengxuechao
 */
@Slf4j
public class Log4j2Config {

    private final DataSource dataSource;

    private final String url="jdbc:mysql://192.168.190.111:3306/zhongchuan?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";

    private final String username="root";

    private final String password="123456";

    private final String driverClassName="com.mysql.cj.jdbc.Driver";

    private Log4j2Config() {
        this.dataSource = new DruidDataSource();
        ((DruidDataSource) this.dataSource).setUrl(url);
        ((DruidDataSource) this.dataSource).setDriverClassName(driverClassName);
        ((DruidDataSource) this.dataSource).setUsername(username);
        ((DruidDataSource) this.dataSource).setPassword(password);
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }

    private static interface Singleton {
        final Log4j2Config INSTANCE = new Log4j2Config();
    }
}

