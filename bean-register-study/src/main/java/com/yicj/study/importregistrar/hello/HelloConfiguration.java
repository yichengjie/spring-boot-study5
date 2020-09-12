package com.yicj.study.importregistrar.hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.yicj.study.importregistrar")
@Import(HelloImportBeanDefinitionRegistrar.class)
public class HelloConfiguration {

}
