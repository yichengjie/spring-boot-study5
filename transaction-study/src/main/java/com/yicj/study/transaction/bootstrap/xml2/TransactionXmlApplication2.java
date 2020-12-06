package com.yicj.study.transaction.bootstrap.xml2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;


@ComponentScan(basePackages = "com.yicj.study.transaction.service")
@ImportResource({"transaction2.xml"})
@SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
public class TransactionXmlApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(TransactionXmlApplication2.class, args) ;
    }
}
