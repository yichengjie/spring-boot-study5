package com.yicj.study.component;

import com.yicj.study.advice.MyMethodInterceptor;
import com.yicj.study.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log.info("=====> beanName: {}执行...postProcessBeforeInstantiation", beanName);
        // 利用其生成动态代理
        if (beanClass == UserServiceImpl.class){
            Enhancer enhancer = new Enhancer() ;
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new MyMethodInterceptor());
            Object o = enhancer.create();
            log.info("返回动态代理..");
            return o ;
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        log.info("=====> beanName: {}执行..postProcessAfterInstantiation", beanName);
        return false;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("=====> beanName: {}执行..postProcessBeforeInitialization", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("=====> beanName: {}执行..postProcessAfterInitialization", beanName);
        return bean;
    }
}
