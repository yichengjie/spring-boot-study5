package com.yicj.study.hello;

import com.yicj.study.common.JUnit4ClassRunner;
import com.yicj.study.config.MyConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class HelloTest{

    @Test
    public void test1(){
        log.info("============");
    }
}
