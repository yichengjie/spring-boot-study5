package com.yicj.study.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @PostMapping("/hello")
    @ResponseBody
    public User hello(@RequestBody  User user){
        return user;
    }

    @Data
    static class User{
        private String id ;
        private String name ;
    }
}
