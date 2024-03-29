package com.yicj.task.component;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

public class ContextCopyingDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        try {
			RequestAttributes context = RequestContextHolder.currentRequestAttributes();  //1
			Map<String,String> previous = MDC.getCopyOfContextMap(); 					  //2
			SecurityContext securityContext = SecurityContextHolder.getContext();	      //3
			return () -> {
			    try {
			    	RequestContextHolder.setRequestAttributes(context);	 //1
			    	MDC.setContextMap(previous);					     //2				
			        SecurityContextHolder.setContext(securityContext);   //3
			        runnable.run();
			    } finally {
			        RequestContextHolder.resetRequestAttributes();		// 1
			        MDC.clear(); 										// 2
			        SecurityContextHolder.clearContext();				// 3
			    }
			};
		} catch (IllegalStateException e) {
			return runnable;
		}
    }
}