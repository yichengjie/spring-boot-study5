package com.yicj.study.aop.hello.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class PerformanceTranceAspect {

    @Pointcut("execution(public * com.yicj.study.aop.hello.service.*.*(..))")
    public void around(){

    }

    @Around("around()")
    public void performanceTrace(ProceedingJoinPoint joinPoint) throws Throwable{
        StopWatch watch = new StopWatch() ;
        try {
            watch.start();
            joinPoint.proceed() ;
        }finally {
            watch.stop();
            log.info("PT in method[{}] >>> {}", joinPoint.getSignature().getName(), watch.toString());
        }
    }

}
