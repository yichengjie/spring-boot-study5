package com.yicj.study.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("目标方法执行前: {}", method.getName() );
        Object obj = methodProxy.invokeSuper(o, objects) ;
        log.info("目标方法执行后：{}", method.getName());
        return obj;
    }
}
