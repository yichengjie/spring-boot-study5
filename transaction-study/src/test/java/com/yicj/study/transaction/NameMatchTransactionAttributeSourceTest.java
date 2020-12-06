package com.yicj.study.transaction;

import com.yicj.study.transaction.service.IQuoteService;
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
    public void test1() throws Exception {
        TransactionAttributeSource tas = this.getTransactionAttribute();
        Method method = IQuoteService.class.getMethod("queryById") ;
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
        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();
        tas.setProperties(transactionAttributes);
        return tas ;
    }
}
