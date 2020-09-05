package com.yicj.study.processor;

import com.yicj.study.service.UserService;
import com.yicj.study.service.decorate.UserServiceDecorate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DecorationPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserService){
            return new UserServiceDecorate((UserService) bean) ;
        }
        return bean;
    }
}
