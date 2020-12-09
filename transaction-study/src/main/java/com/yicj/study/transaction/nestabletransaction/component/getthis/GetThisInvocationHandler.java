package com.yicj.study.transaction.nestabletransaction.component.getthis;

import org.springframework.aop.framework.AopContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GetThisInvocationHandler implements InvocationHandler {
    private Object target ;
    public GetThisInvocationHandler(Object target){
        this.target = target ;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equalsIgnoreCase("getThis")){
            Object p = AopContext.currentProxy();
            return p ;
        }
        return method.invoke(target, args);
    }


    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(GetThisInvocationHandler.class.getClassLoader(),
                target.getClass().getInterfaces(),
                new GetThisInvocationHandler(target)) ;
    }
}
