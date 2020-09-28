package com.yicj.study.metrics;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Timed;
import org.springframework.stereotype.Component;

@Component
public class MockService {
    @Timed
    @Counted
    public void doSth(){
        System.out.println("just do something ...");
    }
}
