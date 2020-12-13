package com.yicj.study.config;

import com.yicj.study.common.datasource.DataSourceType;
import com.yicj.study.common.datasource.ThreadLocalVariableRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DataSourceAutoConfig{

    @Bean("mainDataSource")
    @ConfigurationProperties(prefix = "jdbc.main")
    public DataSource mainDataSource() {
        BasicDataSource dataSource = new BasicDataSource() ;
        return dataSource;
    }

    @Bean("infoDataSource")
    @ConfigurationProperties(prefix = "jdbc.info")
    public DataSource infoDataSource() {
        BasicDataSource dataSource = new BasicDataSource() ;
        return dataSource;
    }

    @Bean
    public DataSource dataSource(){
        ThreadLocalVariableRoutingDataSource dataSource = new ThreadLocalVariableRoutingDataSource() ;
        dataSource.setDefaultTargetDataSource(mainDataSource());
        Map<Object, Object> targetDataSources = new HashMap<>() ;
        targetDataSources.put(DataSourceType.MAIN, mainDataSource()) ;
        targetDataSources.put(DataSourceType.INFO, infoDataSource()) ;
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource ;
    }

}
