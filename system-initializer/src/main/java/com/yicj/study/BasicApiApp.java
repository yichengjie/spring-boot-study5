package com.yicj.study;

import com.yicj.study.initializers.FirstInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApiApp {

    public static void main(String[] args) {
        //方式二：硬编码方式注入系统初始化器
        SpringApplication application = new SpringApplication(BasicApiApp.class);
        application.addInitializers(new FirstInitializer());
        application.run(args) ;
        //SpringApplication.run(BasicApiApp.class, args) ;
    }
}