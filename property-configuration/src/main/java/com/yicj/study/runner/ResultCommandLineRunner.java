package com.yicj.study.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * ClassName: ResultCommandLineRunner
 * Description: TODO(描述)
 * Date: 2020/8/29 20:04
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Component
public class ResultCommandLineRunner implements CommandLineRunner, EnvironmentAware {
    private Environment environment ;
    @Override
    public void run(String... args) throws Exception {
        String property = environment.getProperty("mooc.website.url");
        log.info("=======> prop : {}", property);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment ;
    }
}