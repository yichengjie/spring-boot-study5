package com.yicj.study.mvc.hello.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class StarterWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 根容器的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    // web容器的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // DispatcherServlet拦截地址
    @Override
    protected String[] getServletMappings() {

        return new String[]{"/"};
    }

    // 指定DispatcherServlet中的filter
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true) ;
        return new Filter[]{encodingFilter};
    }
}
