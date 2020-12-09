package com.yicj.study.transaction.nestabletransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication
public class NestableTransactionApplication  {

    public static void main(String[] args) {
        SpringApplication.run(NestableTransactionApplication.class, args) ;
    }
}
