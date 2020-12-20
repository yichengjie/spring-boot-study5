package com.yicj.study.mvc.hello.controller;

import com.yicj.study.mvc.hello.view.HelloExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/excel")
    public ModelAndView excel(){
        List<String> userList = new ArrayList<>() ;
        userList.add("1001") ;
        userList.add("1002") ;
        userList.add("1003") ;
        userList.add("1004") ;
        userList.add("1005") ;
        ModelAndView modelAndView = new ModelAndView() ;
        modelAndView.addObject("userList", userList) ;
        modelAndView.setView(new HelloExcelView());
        return modelAndView ;
    }
}
