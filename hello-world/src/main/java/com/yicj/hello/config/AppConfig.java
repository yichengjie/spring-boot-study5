package com.yicj.hello.config;

import com.yicj.hello.web.filer.MDCInsertingServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<MDCInsertingServletFilter> registrationBean(){
        FilterRegistrationBean<MDCInsertingServletFilter> filter = new FilterRegistrationBean<>() ;
        filter.setFilter(new MDCInsertingServletFilter());
        filter.setUrlPatterns(Arrays.asList("/*"));
        return filter ;
    }
}
