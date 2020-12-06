package com.yicj.study.transaction;

import com.yicj.study.transaction.bootstrap.xml.TransactionXmlApplication4;
import com.yicj.study.transaction.entity.Quote;
import com.yicj.study.transaction.service.IQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionXmlApplication4.class)
public class QuoteServiceXmlTest4 {

    @Resource
    private IQuoteService quoteService ;

    @Test
    public void getQuota(){
        Quote quota = quoteService.queryById();
        log.info("quota : {}", quota);
    }
}
