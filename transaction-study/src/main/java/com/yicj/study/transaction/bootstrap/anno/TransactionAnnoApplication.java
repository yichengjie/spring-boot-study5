package com.yicj.study.transaction.bootstrap.anno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@ComponentScan(basePackages = "com.yicj.study.transaction",excludeFilters = {
    @ComponentScan.Filter(type=FilterType.REGEX, pattern = "com.yicj.study.transaction.bootstrap.*.*")
})
@SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
public class TransactionAnnoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionAnnoApplication.class, args) ;
    }
}
