package com.yicj.study.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName: WeatherSource
 * Description: TODO(描述)
 * Date: 2020/8/30 14:20
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Data
@ConfigurationProperties(prefix = "weather")
public class WeatherSource {
    private String type ;
    private String  rate ;
}