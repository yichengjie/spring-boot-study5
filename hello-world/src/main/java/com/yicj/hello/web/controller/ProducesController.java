package com.yicj.hello.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produces")
public class ProducesController {

    @GetMapping(value = "/hello", produces = "application/json")
    public String hello(){
        return "hello" ;
    }
}
