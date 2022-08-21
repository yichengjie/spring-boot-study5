package com.yicj.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloController {

    @GetMapping("/index")
    public String index(){

        return "index" ;
    }


    @ModelAttribute("message")
    public String message(){
        return "hello world" ;
    }
}
