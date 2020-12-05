package com.yicj.study.transaction.config;

import com.yicj.study.transaction.service.IQuoteService;
import com.yicj.study.transaction.service.impl.QuoteServiceImpl;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean(name = "quoteServiceTarget")
    public IQuoteService quoteServiceTarget(JdbcTemplate jdbcTemplate){
        IQuoteService quoteService = new QuoteServiceImpl(jdbcTemplate) ;
        return quoteService ;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager() ;
        transactionManager.setDataSource(dataSource);
        return transactionManager ;
    }

    @Bean
    public TransactionInterceptor transactionInterceptor(DataSource dataSource){
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor() ;
        transactionInterceptor.setTransactionManager(transactionManager(dataSource));
        Properties properties = new Properties();
        properties.setProperty("getQuote*","PROPAGATION_SUPPORTS,readOnly,timeout_20") ;
        properties.setProperty("saveQuote", "PROPAGATION_REQUIRED") ;
        properties.setProperty("updateQuote","PROPAGATION_REQUIRED") ;
        properties.setProperty("deleteQuote","PROPAGATION_REQUIRED") ;
        transactionInterceptor.setTransactionAttributes(properties);
        return transactionInterceptor ;
    }

    @Qualifier
    @Bean(name = "quoteService")
    public ProxyFactoryBean quoteService(JdbcTemplate jdbcTemplate) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean() ;
        proxyFactoryBean.setTarget(quoteServiceTarget(jdbcTemplate));
        Class<?>[] proxyInterfaces = new Class<?>[]{IQuoteService.class} ;
        proxyFactoryBean.setProxyInterfaces(proxyInterfaces);
        proxyFactoryBean.setInterceptorNames("transactionInterceptor");
        return proxyFactoryBean ;
    }
}
