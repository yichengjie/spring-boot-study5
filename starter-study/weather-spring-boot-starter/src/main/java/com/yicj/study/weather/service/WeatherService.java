package com.yicj.study.weather.service;

import com.yicj.study.weather.config.WeatherSource;

/**
 * ClassName: WeatherServcie
 * Description: TODO(描述)
 * Date: 2020/8/30 14:26
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class WeatherService {
    private final WeatherSource weatherSource ;

    public WeatherService(WeatherSource weatherSource) {
        this.weatherSource = weatherSource;
    }


    public String getType(){
        return weatherSource.getType() ;
    }

    public String getRate(){
        return weatherSource.getRate() ;
    }

}