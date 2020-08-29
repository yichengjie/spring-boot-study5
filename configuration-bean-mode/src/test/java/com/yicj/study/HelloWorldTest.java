package com.yicj.study;

import com.yicj.study.model.Cat;
import com.yicj.study.model.Dog;
import com.yicj.study.model.Monky;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * ClassName: HelloWorldTests
 * Description: TODO(描述)
 * Date: 2020/8/29 19:10
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldTest {

    @Resource
    private Cat cat ;

    @Resource
    private Dog dog ;

    @Autowired
    private Monky monky ;

    @Test
    public void test1(){
        log.info("=====> cat : {}", cat);
        log.info("=====> dog : {}", dog);
        log.info("=====> monky : {}", monky);
    }

}