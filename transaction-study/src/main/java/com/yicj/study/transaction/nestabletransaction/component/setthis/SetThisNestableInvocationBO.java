package com.yicj.study.transaction.nestabletransaction.component.setthis;

import org.springframework.stereotype.Service;

@Service
public class SetThisNestableInvocationBO implements SetThisAware<SetThisNestableInvocationBO> {

    private SetThisNestableInvocationBO bo ;

    public void method1(){
        bo.method2();
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }


    @Override
    public void setThis(SetThisNestableInvocationBO nestableInvocationBO) {
        this.bo = nestableInvocationBO ;
    }
}
