package com.yicj.study.postprocessor.hello;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MyConfig.class})////表示只需要这一个文件
public class HelloApplicationTest {

    @Autowired
    private HelloService helloService ;

    @Test
    public void test1(){
        helloService.hello("yicj");
    }

}
