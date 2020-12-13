package com.yicj.study.postprocessor.hello.service.impl;

import com.yicj.study.postprocessor.hello.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello(String name){
        System.out.println("=====> " + name);
    }
}
