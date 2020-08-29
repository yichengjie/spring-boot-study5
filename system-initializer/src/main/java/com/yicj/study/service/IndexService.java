package com.yicj.study.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ClassName: IndexService
 * Description: TODO(描述)
 * Date: 2020/8/29 11:42
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
public class IndexService implements ApplicationContextAware {
    private ApplicationContext applicationContext ;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
    }

    public String getKeyValue(String key){
        return applicationContext.getEnvironment().getProperty(key) ;
    }
}