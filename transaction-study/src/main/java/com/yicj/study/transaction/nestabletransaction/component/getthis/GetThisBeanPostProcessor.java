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
        if (bean instanceof GetThisAware){
            return getProxyFactoryProxy(bean) ;
        }
        return bean;
    }

    private Object getMySelfProxy(Object bean){
        return GetThisInvocationHandler.getProxy(bean) ;
    }

    private Object getProxyFactoryProxy(Object bean){
        GetThisAware delegate = new GetThisAwareImpl() ;
        DelegatingIntroductionInterceptor interceptor =
                new DelegatingIntroductionInterceptor(delegate) ;
        ProxyFactory weaver = new ProxyFactory(bean) ;
        weaver.setProxyTargetClass(true);
        weaver.addAdvice(interceptor);
        Object proxy = weaver.getProxy();
        return proxy ;
    }
}
