package com.yicj.study.filter;

import com.yicj.study.advice.BufferedHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

//https://blog.csdn.net/qq_40233503/article/details/104592362
@Slf4j
@Order(0)
@Component
public class RequestBufferedFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request = new BufferedHttpRequest(request) ;
        filterChain.doFilter(request, response);
        ConcurrentLinkedQueue<BufferedHttpRequest.BufferedServletInputStream> concurrentLinkedQueue = ((BufferedHttpRequest) request).getConcurrentLinkedQueue();
        concurrentLinkedQueue.forEach(item -> IOUtils.closeQuietly(item));
    }
}
