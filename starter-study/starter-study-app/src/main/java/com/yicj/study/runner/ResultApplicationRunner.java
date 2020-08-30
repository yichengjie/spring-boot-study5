package com.yicj.study.runner;

import com.yicj.study.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ClassName: ResultApplicationRunner
 * Description: TODO(描述)
 * Date: 2020/8/30 14:40
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Component
public class ResultApplicationRunner implements ApplicationRunner {

    @Resource
    private WeatherService weatherService ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=====> weatherService type :{} ", weatherService.getType());
        log.info("=====> weatherService rate :{} ", weatherService.getRate());
    }
}