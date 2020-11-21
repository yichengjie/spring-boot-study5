package com.yicj.study.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PostMapping("/hello")
    @ResponseBody
    public String hello(String id, String name){
        return "id = " + id +", name : " + name ;
    }
}
