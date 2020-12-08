package com.yicj.study.introductioninterceptor.service;

import com.yicj.study.introductioninterceptor.service.impl.DeveloperImpl;
import com.yicj.study.introductioninterceptor.service.impl.TesterImpl;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class DeveloperImplTest {

    @Test
    public void test1(){
        IDeveloper target = new DeveloperImpl() ;
        ITester delegate = new TesterImpl() ;
        DelegatingIntroductionInterceptor interceptor =
                new DelegatingIntroductionInterceptor(delegate) ;
        // 进行织入
        ProxyFactory weaver = new ProxyFactory(target) ;
        weaver.addAdvice(interceptor);
        ITester proxy = (ITester)weaver.getProxy();
        proxy.testSoftware();
    }
}
