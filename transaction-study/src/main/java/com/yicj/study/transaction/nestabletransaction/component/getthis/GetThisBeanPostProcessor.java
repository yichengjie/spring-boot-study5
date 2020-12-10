package com.yicj.study.transaction.nestabletransaction.component.getthis;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class GetThisBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GetThisProxyAware){
            //return getProxyFactoryProxy(bean) ;
            return getMySelfProxy(bean) ;
        }
        if (bean instanceof GetThisProxy){
            return getMySelfProxy(bean) ;
        }
        return bean;
    }

    private Object getMySelfProxy(Object bean){
        return GetThisInvocationHandler.getProxy(bean) ;
    }

    private Object getProxyFactoryProxy(Object bean){
        GetThisProxy delegate = new GetThisProxyImpl() ;
        DelegatingIntroductionInterceptor interceptor =
                new DelegatingIntroductionInterceptor(delegate) ;
        ProxyFactory weaver = new ProxyFactory(bean) ;
        weaver.setProxyTargetClass(true);
        weaver.setExposeProxy(true);
        weaver.addAdvice(interceptor);
        GetThisProxy proxy = (GetThisProxy)weaver.getProxy();
        return proxy ;
    }
}
