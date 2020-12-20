package com.yicj.study.mvc.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {

    @GetMapping("/helloworld")
    public String helloworld(){
        return "helloworld" ;
    }
}
