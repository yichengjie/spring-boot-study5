package com.yicj.study.mvc.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello world" ;
    }

    @GetMapping("/helloworld")
    public String helloworld(){

        return "helloworld" ;
    }
}
