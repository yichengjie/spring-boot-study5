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
@ImportResource({"transaction4.xml"})
@SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
public class TransactionXmlApplication4 {
    public static void main(String[] args) {
        SpringApplication.run(TransactionXmlApplication4.class, args) ;
    }
}
