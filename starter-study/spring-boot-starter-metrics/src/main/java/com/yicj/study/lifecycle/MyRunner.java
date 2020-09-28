package com.yicj.study.lifecycle;

import com.yicj.study.metrics.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private MockService mockService ;

    @Override
    public void run(String... args) throws Exception {

        mockService.doSth();
    }
}
