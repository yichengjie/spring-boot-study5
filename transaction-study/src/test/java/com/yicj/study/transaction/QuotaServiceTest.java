package com.yicj.study.transaction;

import com.yicj.study.transaction.entity.Quota;
import com.yicj.study.transaction.service.IQuotaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
public class QuotaServiceTest {

    @Autowired
    private IQuotaService quotaService ;

    @Test
    public void getQuota(){
        Quota quota = quotaService.getQuota();
        log.info("quota : {}", quota);
    }
}
