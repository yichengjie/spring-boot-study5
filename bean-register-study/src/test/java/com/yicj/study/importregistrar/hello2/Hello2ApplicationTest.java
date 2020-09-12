package com.yicj.study.importregistrar.hello2;

import com.yicj.study.importregistrar.hello2.config.Hello2Configuration;
import com.yicj.study.importregistrar.hello2.service.Hello2Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {Hello2Configuration.class})////表示只需要这一个文件
public class Hello2ApplicationTest {

    @Resource
    private Hello2Service helloService ;

    @Test
    public void contextLoads(){
        log.info("service name : {}", helloService.getClass());
    }
}
