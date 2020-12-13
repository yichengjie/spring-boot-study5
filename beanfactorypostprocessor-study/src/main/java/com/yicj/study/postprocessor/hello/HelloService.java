package com.yicj.study.postprocessor.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public void hello(String name){
        System.out.println("=====> " + name);
    }
}
