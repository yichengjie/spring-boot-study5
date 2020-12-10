package com.yicj.study.transaction.nestabletransaction.component.apocontext;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class AopContextNestableInvocationBO {

    private AopContextNestableInvocationBO bo ;

    public void method1(){
        Object p = AopContext.currentProxy();
        ((AopContextNestableInvocationBO)p).method2();
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }

}