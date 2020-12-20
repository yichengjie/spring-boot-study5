package com.yicj.study.aop.hello.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext context ;

    public static <T> T getBean(String beanName, Class<T> clazz){
        return context.getBean(beanName, clazz) ;
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName) ;
    }

    public static <T> T getBean(Class<T> clzzz){
        return context.getBean(clzzz) ;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext ;
    }
}
