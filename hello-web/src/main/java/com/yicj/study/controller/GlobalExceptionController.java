package com.yicj.study.controller;

import com.yicj.study.exception.BaseException;
import com.yicj.study.exception.DuplicateKeyException;
import com.yicj.study.exception.TokenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class GlobalExceptionController {

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id){
        if ("1".equals(id)){
            throw new BaseException("base exception 1") ;
        }else if ("2".equals(id)){
            throw new TokenException("token exception 2") ;
        }else if ("3".equals(id)){
            throw new DuplicateKeyException("duplicate key 3") ;
        }
        return "hello world";
    }
}
