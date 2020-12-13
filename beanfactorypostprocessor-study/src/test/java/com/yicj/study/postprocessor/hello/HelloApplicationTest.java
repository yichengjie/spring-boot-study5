package com.yicj.study.postprocessor.hello;

import com.yicj.study.postprocessor.hello.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
//@ContextConfiguration(classes = {MyConfig.class})
public class HelloApplicationTest {

    @Autowired
    private HelloService helloService ;

    @Test
    public void test1() throws SQLException {
        helloService.hello("yicj");
    }

}
