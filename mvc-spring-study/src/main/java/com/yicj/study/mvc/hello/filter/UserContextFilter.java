package com.yicj.study.mvc.hello.filter;

import com.yicj.study.mvc.hello.util.UserContextHolder;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "userContextFilter")
public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        System.out.println("---------------------");
    }
}
