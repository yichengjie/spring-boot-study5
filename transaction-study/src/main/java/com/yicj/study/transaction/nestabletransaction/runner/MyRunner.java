package com.yicj.study.transaction.nestabletransaction.runner;

import com.yicj.study.transaction.nestabletransaction.component.apocontext.AopContextNestableInvocationBO;
import com.yicj.study.transaction.nestabletransaction.component.getthis.GetThisNestableInvocationBO;
import com.yicj.study.transaction.nestabletransaction.component.setthis.SetThisNestableInvocationBO;
import com.yicj.study.transaction.nestabletransaction.component.superclass.SuperClassNestableInvocationBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyRunner implements ApplicationRunner {
    @Autowired
    private SetThisNestableInvocationBO setThisNestableInvocationBO ;
    @Autowired
    private GetThisNestableInvocationBO getThisNestableInvocationBO ;
    @Autowired
    private AopContextNestableInvocationBO aopContextNestableInvocationBO ;
    @Autowired
    private SuperClassNestableInvocationBO superClassNestableInvocationBO ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        invokeGetBoBusi();
        //invokeSetBoBusi();
        //this.invokeAopContextBusi();
        //invokeSuperClassBusi();
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
        System.out.println("-----------------------");
        //Object aThis = getThisNestableInvocationBO.getThis();
        //log.info("=======> " + aThis);
    }

    private void invokeAopContextBusi(){
        aopContextNestableInvocationBO.method2();
        System.out.println("-----------------------");
        aopContextNestableInvocationBO.method1();
    }

    private void invokeSuperClassBusi(){
        superClassNestableInvocationBO.method2();
        System.out.println("-----------------------");
        superClassNestableInvocationBO.method1();
    }
}
