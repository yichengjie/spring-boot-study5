package com.yicj.study.aop;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static com.codahale.metrics.MetricRegistry.name;

@Aspect
@Component
public class AutoMetricsAspect {

    protected ConcurrentMap<String, Meter> meters = new ConcurrentHashMap<>() ;
    protected ConcurrentMap<String,Meter> exceptionMeters = new ConcurrentHashMap<>() ;
    protected ConcurrentMap<String, Timer> timers = new ConcurrentHashMap<>() ;
    protected ConcurrentMap<String, Counter> counters = new ConcurrentHashMap<>() ;

    @Autowired
    private MetricRegistry metricRegistry ;

    @Pointcut("execution(public * * (..))")
    public void publicMethod(){}

    @Before("publicMethod() && @annotation(countedAnnotation)")
    public void instrumentCounted(JoinPoint jp, Counted countedAnnotation){
        String name = name(jp.getTarget().getClass(),
                           StringUtils.hasLength(countedAnnotation.name()) ? countedAnnotation.name() : jp.getSignature().getName(),
                "counter") ;
        Counter counter = counters.computeIfAbsent(name, key -> metricRegistry.counter(key)) ;
        counter.inc();
    }

    @Before("publicMethod() && @annotation(meteredAnnotation)")
    public void instrumentMetered(JoinPoint jp, Metered meteredAnnotation){
        String name = name(jp.getTarget().getClass(),
                StringUtils.hasLength(meteredAnnotation.name()) ? meteredAnnotation.name(): jp.getSignature().getName(),
                "meter") ;
        Meter meter = meters.computeIfAbsent(name, key -> metricRegistry.meter(key)) ;
        meter.mark();
    }

    @AfterThrowing(pointcut = "publicMethod() && @annotation(exMeteredAnnotation)", throwing = "ex")
    public void instrumentExceptionMetered(JoinPoint jp, Throwable ex, ExceptionMetered exMeteredAnnotation){
        String name = name(jp.getTarget().getClass(),
                StringUtils.hasLength(exMeteredAnnotation.name()) ? exMeteredAnnotation.name() : jp.getSignature().getName(),
                "exception") ;
        Meter meter = exceptionMeters.computeIfAbsent(name, meterName -> metricRegistry.meter(meterName));
        meter.mark();
    }

    @Around("publicMethod() && @annotation(timedAnnotation)")
    public Object instrumentTimed(ProceedingJoinPoint pjp, Timed timedAnnotation) throws Throwable{
        String name = name(pjp.getTarget().getClass(),
                StringUtils.hasLength(timedAnnotation.name()) ? timedAnnotation.name(): pjp.getSignature().getName(),
                "timer") ;
        Timer timer = timers.computeIfAbsent(name, inputName -> metricRegistry.timer(inputName));
        Timer.Context tc = timer.time();
        try {
            return pjp.proceed() ;
        }finally {
            tc.stop() ;
        }
    }

}
