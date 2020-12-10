package com.yicj.study.transaction.nestabletransaction.runner;

import com.yicj.study.transaction.nestabletransaction.component.apocontext.AopContextNestableInvocationBO;
import com.yicj.study.transaction.nestabletransaction.component.getthis.GetThisNestableInvocationBO;
import com.yicj.study.transaction.nestabletransaction.component.setthis.SetThisNestableInvocationBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    @Autowired
    private SetThisNestableInvocationBO setThisNestableInvocationBO ;
    @Autowired
    private GetThisNestableInvocationBO getThisNestableInvocationBO ;
    @Autowired
    private AopContextNestableInvocationBO aopContextNestableInvocationBO ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //invokeGetBoBusi();
        //invokeSetBoBusi();
        this.invokeAopContextBusi();
    }

    private void invokeSetBoBusi(){
        setThisNestableInvocationBO.method2();
        System.out.println("-----------------------");
        setThisNestableInvocationBO.method1();
    }

    private void invokeGetBoBusi(){
        getThisNestableInvocationBO.method2();
        System.out.println("-----------------------");
        getThisNestableInvocationBO.method1();
    }

    private void invokeAopContextBusi(){
        aopContextNestableInvocationBO.method2();
        System.out.println("-----------------------");
        aopContextNestableInvocationBO.method1();
    }
}
