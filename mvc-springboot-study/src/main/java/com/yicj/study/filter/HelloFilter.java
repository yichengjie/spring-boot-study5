package com.yicj.study.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(1)
@Component
public class HelloFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        byte [] bytes = new byte[1024] ;
        request.getInputStream().read(bytes);
        String content = new String(bytes, "UTF-8");
        log.info("过滤器拿到请求数据 :{}", content);
        filterChain.doFilter(request, response);
    }
}
