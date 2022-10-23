package com.yicj.hello.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumes")
public class ConsumesController {

    @GetMapping(value = "/hello", consumes = {"application/json"})
    public String hello(){
        return "hello world" ;
    }
}
