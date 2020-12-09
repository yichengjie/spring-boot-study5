package com.yicj.study.transaction.nestabletransaction.component.getthis;

import org.springframework.aop.framework.AopContext;

public class GetThisAwareImpl implements GetThisAware {

    @Override
    public Object getThis() {
        Object proxy = AopContext.currentProxy();
        return proxy ;
    }
}
