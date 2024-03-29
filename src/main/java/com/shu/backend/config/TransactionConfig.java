package com.shu.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
