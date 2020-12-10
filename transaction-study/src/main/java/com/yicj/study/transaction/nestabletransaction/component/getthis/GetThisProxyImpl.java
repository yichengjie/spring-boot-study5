package com.yicj.study.transaction.nestabletransaction.component.getthis;

import org.springframework.aop.framework.AopContext;

public class GetThisProxyImpl implements GetThisProxy {
    @Override
    public Object getThis() {
        return AopContext.currentProxy();
    }
}
