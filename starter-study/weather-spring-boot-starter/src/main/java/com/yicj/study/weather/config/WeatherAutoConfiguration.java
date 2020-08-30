package com.yicj.study.weather.config;

import com.yicj.study.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: WeatherAutoConfiguration
 * Description: TODO(描述)
 * Date: 2020/8/30 14:28
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Configuration
@EnableConfigurationProperties(WeatherSource.class)
@ConditionalOnProperty(name = "weather.enable", havingValue = "enable")
public class WeatherAutoConfiguration {
    @Autowired
    private WeatherSource weatherSource ;

    @Bean
    @ConditionalOnMissingBean(WeatherService.class)
    public WeatherService weatherService(){

        return new WeatherService(weatherSource) ;
    }
}