package com.yicj.study.transaction.bootstrap.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;


@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type= FilterType.REGEX, pattern = "com.yicj.study.transaction.bootstrap.*.*")
})
@ImportResource({"transaction3.xml"})
@SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
public class TransactionXmlApplication3 {
    public static void main(String[] args) {
        SpringApplication.run(TransactionXmlApplication3.class, args) ;
    }
}
