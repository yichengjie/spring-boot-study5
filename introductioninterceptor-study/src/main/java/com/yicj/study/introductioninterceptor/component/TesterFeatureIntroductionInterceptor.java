package com.yicj.study.introductioninterceptor.component;

import com.yicj.study.introductioninterceptor.service.ITester;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class TesterFeatureIntroductionInterceptor extends DelegatingIntroductionInterceptor implements ITester {

    private boolean busyAsTester ;

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (isBusyAsTester()){
            throw new RuntimeException("你想累死我呀?") ;
        }
        return super.invoke(mi);
    }

    public boolean isBusyAsTester() {
        return busyAsTester;
    }

    @Override
    public void testSoftware() {
        this.setBusyAsTester(true);
        System.out.println("I will ensure the quality.");
    }

    public void setBusyAsTester(boolean busyAsTester) {
        this.busyAsTester = busyAsTester;
    }

}
