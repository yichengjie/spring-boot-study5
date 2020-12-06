package com.yicj.study.transaction.bootstrap.anno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.yicj.study.transaction")
@SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
public class TransactionAnnoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionAnnoApplication.class, args) ;
    }
}
