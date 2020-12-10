package com.yicj.study.transaction.nestabletransaction.component.superclass;

import com.yicj.study.transaction.nestabletransaction.component.getthis.GetThisProxyImpl;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class SuperClassNestableInvocationBO extends GetThisProxyImpl {

    private SuperClassNestableInvocationBO bo ;

    public void method1(){
        SuperClassNestableInvocationBO aThis = (SuperClassNestableInvocationBO)getThis();
        aThis.method2();
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }

}