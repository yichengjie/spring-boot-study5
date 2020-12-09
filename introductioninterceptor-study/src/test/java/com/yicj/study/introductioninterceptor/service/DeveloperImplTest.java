package com.yicj.study.introductioninterceptor.service;

import com.yicj.study.introductioninterceptor.component.TesterFeatureIntroductionInterceptor;
import com.yicj.study.introductioninterceptor.service.impl.DeveloperImpl;
import com.yicj.study.introductioninterceptor.service.impl.TesterImpl;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
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

    @Test
    public void test2(){
        ProxyFactory weaver = new ProxyFactory(new DeveloperImpl()) ;
        weaver.setInterfaces(new Class[]{IDeveloper.class, ITester.class});
        TesterFeatureIntroductionInterceptor advice = new TesterFeatureIntroductionInterceptor() ;
        weaver.addAdvice(advice);
        /*DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor(advice,advice) ;
        weaver.addAdvisor(advisor);*/
        Object proxy = weaver.getProxy();
        ((ITester)proxy).testSoftware();
        ((IDeveloper)proxy).developSoftware();
    }
}
