package com.yicj.study.transaction.nestabletransaction;

public class NestableInvocationBO {

    public void method1(){
        method2();
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }
}
