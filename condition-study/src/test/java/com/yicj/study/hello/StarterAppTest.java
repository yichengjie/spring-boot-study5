package com.yicj.study.hello;

import com.yicj.study.hello.condition.A;
import com.yicj.study.hello.condition.B;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: StarterAppTest
 * Description: TODO(描述)
 * Date: 2020/8/30 13:39
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StarterAppTest implements ApplicationContextAware {
    private ApplicationContext applicationContext ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
    }

    @Test
    public void testA(){
        A bean = applicationContext.getBean(A.class);
        log.info("bean : {}", bean);
    }

    @Test
    public void testB(){
        B bean = applicationContext.getBean(B.class);
        log.info("bean : {}", bean);
    }
}