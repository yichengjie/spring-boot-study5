package com.yicj.study.transaction.nestabletransaction.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SetThisBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SetThisAware){
            SetThisAware bb = (SetThisAware)bean ;
            bb.getThis(bean);
        }
        return null;
    }
}
