package com.yicj.study.transaction.nestabletransaction.busi;

import com.yicj.study.transaction.nestabletransaction.component.SetThisAware;
import org.springframework.stereotype.Service;

@Service
public class NestableInvocationBO implements SetThisAware<NestableInvocationBO> {

    private NestableInvocationBO bo ;

    public void method1(){
        // 非spring环境单元测试式，这里是null
        if (bo ==null){
            this.method2();
        }else {
            bo.method2();
        }
        System.out.println("method1 executed !");
    }

    public void method2(){
        System.out.println("method2 executed !");
    }


    @Override
    public void setThis(NestableInvocationBO nestableInvocationBO) {
        this.bo = nestableInvocationBO ;
    }
}
