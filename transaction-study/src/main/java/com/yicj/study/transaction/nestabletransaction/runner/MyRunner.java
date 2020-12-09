package com.yicj.study.transaction.nestabletransaction.runner;

import com.yicj.study.transaction.nestabletransaction.busi.NestableInvocationBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {

    @Autowired
    private NestableInvocationBO nestableInvocationBO ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nestableInvocationBO.method2();
        System.out.println("-----------------------");
        nestableInvocationBO.method1();
    }
}
