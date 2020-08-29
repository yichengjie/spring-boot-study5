package com.yicj.study.initializers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: FirstInitializer
 * Description: TODO(描述)
 * Date: 2020/8/29 11:26
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
public class SecondInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Map<String,Object> map = new HashMap<>() ;
        map.put("key2", "value2") ;
        MapPropertySource mapPropertySource = new MapPropertySource("secondInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);

        log.info("======> run secondInitializer");
    }
}