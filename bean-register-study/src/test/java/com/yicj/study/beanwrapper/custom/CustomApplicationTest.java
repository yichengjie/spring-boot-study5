package com.yicj.study.beanwrapper.custom;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperAutoConfig.class)
//只将MapperAutoConfig类纳入测试环境的spring容器中
//或@ContextConfiguration(classes = {MapperAutoConfig.class})
public class CustomApplicationTest {
    @Resource
    private CountryMapper mapper ;

    @Test
    public void contextLoads(){
        log.info("count mapper class : {}", mapper.getClass().getName());
    }
}
