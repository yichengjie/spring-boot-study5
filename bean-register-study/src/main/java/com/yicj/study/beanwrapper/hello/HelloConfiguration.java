package com.yicj.study.beanwrapper.hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.yicj.study.beanwrapper.hello")
@Import(HelloImportBeanDefinitionRegistrar.class)
public class HelloConfiguration {

}
