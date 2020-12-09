package com.yicj.study.transaction.nestabletransaction.component.getthis;

import org.springframework.stereotype.Component;

@Component
public class GetThisNestableInvocationBO implements GetThisAware{

    public void method1(){
        ((GetThisNestableInvocationBO)this.getThis()).method2();
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }

    @Override
    public Object getThis() {
        return null;
    }
}
