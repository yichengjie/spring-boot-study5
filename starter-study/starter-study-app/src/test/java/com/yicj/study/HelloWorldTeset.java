package com.yicj.study;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;

/**
 * ClassName: HelloWorldTeset
 * Description: TODO(描述)
 * Date: 2020/8/30 15:40
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class HelloWorldTeset {

    @Test
    public void test1(){
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,
                ClassUtils.getDefaultClassLoader());
        configurations.forEach(item -> log.info("item name : {}", item));
    }
}