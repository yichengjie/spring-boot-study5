package com.yicj.study.transaction;

import com.yicj.study.transaction.service.IQuoteService;
import com.yicj.study.transaction.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

import java.lang.reflect.Method;
import java.util.Properties;

@Slf4j
public class NameMatchTransactionAttributeSourceTest {

    @Test
    public void queryById() throws Exception {
        TransactionAttributeSource tas = this.getTransactionAttribute();
        Method method = IUserService.class.getMethod("queryById") ;
        Class<?> targetClass = IQuoteService.class ;
        TransactionAttribute transactionAttribute = tas.getTransactionAttribute(method, targetClass);
        log.info("transactionAttribute : {}", transactionAttribute);
    }

    @Test
    public void hello() throws Exception {
        TransactionAttributeSource tas = this.getTransactionAttribute();
        Method method = IUserService.class.getMethod("hello") ;
        Class<?> targetClass = IQuoteService.class ;
        TransactionAttribute transactionAttribute = tas.getTransactionAttribute(method, targetClass);
        log.info("transactionAttribute : {}", transactionAttribute);
    }

    private TransactionAttributeSource getTransactionAttribute(){
        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty("queryById*","PROPAGATION_SUPPORTS,readOnly,timeout_20") ;
        transactionAttributes.setProperty("saveQuote", "PROPAGATION_REQUIRED") ;
        transactionAttributes.setProperty("updateQuote","PROPAGATION_REQUIRED") ;
        transactionAttributes.setProperty("deleteQuote","PROPAGATION_REQUIRED") ;
        transactionAttributes.setProperty("com.yicj.study.transaction.service.IUserService.*","PROPAGATION_REQUIRED") ;

        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();
        tas.setProperties(transactionAttributes);
        return tas ;
    }
}
