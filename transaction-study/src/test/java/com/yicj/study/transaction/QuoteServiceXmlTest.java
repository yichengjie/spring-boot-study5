package com.yicj.study.transaction;

import com.yicj.study.transaction.bootstrap.anno.TransactionAnnoApplication;
import com.yicj.study.transaction.bootstrap.xml.TransactionXmlApplication;
import com.yicj.study.transaction.entity.Quote;
import com.yicj.study.transaction.service.IQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionXmlApplication.class)
public class QuoteServiceXmlTest {

    @Autowired
    private IQuoteService quoteService ;

    @Test
    public void getQuota(){
        Quote quota = quoteService.getQuote();
        log.info("quota : {}", quota);
    }
}
