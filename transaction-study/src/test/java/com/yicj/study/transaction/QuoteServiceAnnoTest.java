package com.yicj.study.transaction;

import com.yicj.study.transaction.bootstrap.anno.TransactionAnnoApplication;
import com.yicj.study.transaction.entity.Quote;
import com.yicj.study.transaction.service.IQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionAnnoApplication.class)
public class QuoteServiceAnnoTest {

    @Resource
    private IQuoteService quoteService ;

    @Autowired
    private ApplicationContext applicationContext ;

    @Test
    public void getQuota(){
        Quote quota = quoteService.queryById();
        log.info("quota : {}", quota);
    }
}
