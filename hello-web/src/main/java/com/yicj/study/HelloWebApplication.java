package com.yicj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HelloWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWebApplication.class, args) ;
    }
}
