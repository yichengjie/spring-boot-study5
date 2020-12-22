package com.yicj.study.mvc.hello.controller;

import com.yicj.study.mvc.hello.util.UserContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class JspController {

    @GetMapping("/helloworld")
    public String helloworld(HttpSession session){
        session.setAttribute("token","123456");
        session.setAttribute("xtoken", "1234567");
        session.setAttribute("username", "yicj");
        return "helloworld" ;
    }

    @GetMapping("/redirectHello")
    public ModelAndView redirectHello(HttpSession session) {
        UserContextHolder.setToken("123456");
        UserContextHolder.setXToken("1234567");
        UserContextHolder.setUserName("yicj");
        session.setAttribute("token","123456");
        session.setAttribute("xtoken", "1234567");
        session.setAttribute("username", "yicj");
        return new ModelAndView("/pages/redirectHello.jsp");
    }
}
