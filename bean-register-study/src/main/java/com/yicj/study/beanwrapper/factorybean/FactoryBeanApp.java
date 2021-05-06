package com.yicj.study.beanwrapper.factorybean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(UserFactoryBean.class);
        context.refresh();
        context.registerBeanDefinition("user", builder.getBeanDefinition());
        System.out.println("获取user:"+context.getBean("user"));
        System.out.println("获取userFactoryBean:"+context.getBean("&user"));
    }
}