package com.yicj.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MetricsApp {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MetricsApp.class, args) ;
        System.in.read();
    }
}
