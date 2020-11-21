package com.yicj.study;

import com.yicj.study.common.ResultMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext ;
    }

    public static ResultMapper getBean4ResultMapper(String beanName){
        return (ResultMapper)context.getBean(beanName) ;
    }
}
