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
            GetThisAware delegate = new GetThisAwareImpl() ;
            DelegatingIntroductionInterceptor interceptor =
                    new DelegatingIntroductionInterceptor(delegate) ;
            ProxyFactory weaver = new ProxyFactory(bean) ;
            weaver.addAdvice(interceptor);
            GetThisAware proxy = (GetThisAware)weaver.getProxy();
            return proxy ;
        }
        return bean;
    }
}
