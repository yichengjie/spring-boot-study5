package com.yicj.study.transaction.nestabletransaction.component.getthis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetThisNestableInvocationBO implements GetThisProxy {

    public void method1(){
        GetThisNestableInvocationBO aThis = (GetThisNestableInvocationBO) this.getThis();
        aThis.method2();
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
