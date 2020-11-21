package com.yicj.study.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class RequestBufferedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter init ...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        byte [] bytes = new byte[1024] ;
        request.getInputStream().read(bytes);
        String content = new String(bytes, "UTF-8");
        log.info("过滤器拿到请求数据 :{}", content);
        chain.doFilter(request, response);
    }
}
