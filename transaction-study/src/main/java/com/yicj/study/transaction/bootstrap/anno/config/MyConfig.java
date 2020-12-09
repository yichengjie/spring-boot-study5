package com.yicj.study.transaction.bootstrap.anno.config;

import com.yicj.study.transaction.service.IQuoteService;
import com.yicj.study.transaction.service.impl.QuoteServiceImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MyConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties ;

    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dataSourceProperties.getUrl());
        basicDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        basicDataSource.setUsername(dataSourceProperties.getUsername());
        basicDataSource.setPassword(dataSourceProperties.getPassword());
        return basicDataSource ;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate() ;
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate ;
    }



    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager() ;
        transactionManager.setDataSource(dataSource());
        return transactionManager ;
    }

    @Bean
    public TransactionInterceptor transactionInterceptor(){
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor() ;
        transactionInterceptor.setTransactionManager(transactionManager());
        Properties properties = new Properties();
        properties.setProperty("queryById*","PROPAGATION_SUPPORTS,readOnly,timeout_20") ;
        properties.setProperty("saveQuote", "PROPAGATION_REQUIRED") ;
        properties.setProperty("updateQuote","PROPAGATION_REQUIRED") ;
        properties.setProperty("deleteQuote","PROPAGATION_REQUIRED") ;
        transactionInterceptor.setTransactionAttributes(properties);
        return transactionInterceptor ;
    }

    @Bean
    public IQuoteService quoteServiceTarget(JdbcTemplate jdbcTemplate){
        return new QuoteServiceImpl(jdbcTemplate);
    }

    @Bean
    public ProxyFactoryBean quoteService(JdbcTemplate jdbcTemplate) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean() ;
        proxyFactoryBean.setTarget(quoteServiceTarget(jdbcTemplate));
        Class<?>[] proxyInterfaces = new Class<?>[]{IQuoteService.class} ;
        proxyFactoryBean.setProxyInterfaces(proxyInterfaces);
        proxyFactoryBean.setInterceptorNames("transactionInterceptor");
        return proxyFactoryBean ;
    }
}
