package com.yicj.study.transaction.nestabletransaction;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

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

}
