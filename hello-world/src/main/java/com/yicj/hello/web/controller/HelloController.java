package com.yicj.hello.web.controller;

import ch.qos.logback.classic.ClassicConstants;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        //MDC.put(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY, request.getRemoteHost());
        String host = MDC.get(ClassicConstants.REQUEST_REMOTE_HOST_MDC_KEY);

        return host;
    }
}
