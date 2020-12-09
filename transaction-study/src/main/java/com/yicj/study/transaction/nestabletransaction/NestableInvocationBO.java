package com.yicj.study.transaction.nestabletransaction;

public class NestableInvocationBO implements SetThisAware<NestableInvocationBO> {

    private NestableInvocationBO bo ;

    public void method1(){
        bo.method2();
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }


    @Override
    public void getThis(NestableInvocationBO nestableInvocationBO) {
        this.bo = nestableInvocationBO ;
    }
}
