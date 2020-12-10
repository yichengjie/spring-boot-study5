package com.yicj.study.transaction.nestabletransaction;

import com.yicj.study.transaction.nestabletransaction.busi.NestableInvocationBO;
import com.yicj.study.transaction.nestabletransaction.component.PerformanceTranceAspect;
import com.yicj.study.transaction.nestabletransaction.component.getthis.GetThisProxy;
import com.yicj.study.transaction.nestabletransaction.component.getthis.GetThisProxyImpl;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class NestableTransactionTest {

    @Test
    public void test1(){
        AspectJProxyFactory weaver = new AspectJProxyFactory(new NestableInvocationBO()) ;
        weaver.setProxyTargetClass(true);
        weaver.addAspect(PerformanceTranceAspect.class);

        NestableInvocationBO proxy = weaver.getProxy();
        proxy.method2();
        System.out.println("-----------------------");
        proxy.method1();
    }


    @Test
    public void test2(){
        GetThisProxy delegate = new GetThisProxyImpl() ;
        DelegatingIntroductionInterceptor interceptor =
                new DelegatingIntroductionInterceptor(delegate) ;

        NestableInvocationBO target = new NestableInvocationBO() ;
        ProxyFactory weaver = new ProxyFactory(target) ;
        weaver.setProxyTargetClass(true);
        weaver.addAdvice(interceptor);
        GetThisProxy proxy = (GetThisProxy)weaver.getProxy();

        Object aThis = proxy.getThis();
        System.out.println(aThis);
    }

}
