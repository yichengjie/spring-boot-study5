package com.yicj.study.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * ClassName: ResultApplicationRunner
 * Description: TODO(描述)
 * Date: 2020/8/30 10:44
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Component
public class ResultApplicationRunner implements ApplicationRunner, EnvironmentAware {

    private Environment env ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String prop1 = env.getProperty("mooc");
        log.info("=====> mooc : {}", prop1);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment ;
    }
}