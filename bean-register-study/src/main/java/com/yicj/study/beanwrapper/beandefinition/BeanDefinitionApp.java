package com.yicj.study.beanwrapper.beandefinition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class BeanDefinitionApp {

    public static void main(String[] args) {
        // 首先获取容器上下文
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class) ;
        // 生成java类对应的BeanDefinitionBuilder
        BeanDefinitionBuilder builder =
                BeanDefinitionBuilder.genericBeanDefinition(Student.class) ;
        //将BeanDefinition注册到该spring容器上
        context.registerBeanDefinition("student",builder.getBeanDefinition());
        // 尝试获取
        Object student = context.getBean("student");
        log.info("student : {}", student);
    }
}
