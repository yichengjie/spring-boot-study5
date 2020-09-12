package com.yicj.study.importregistrar;

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
@ContextConfiguration(classes = {HelloConfiguration.class})////表示只需要这一个文件
public class HelloApplicationTest {

    @Resource
    private HelloService helloService ;

    @Test
    public void contextLoads(){
        log.info("service name : {}", helloService.getClass());
    }

}
