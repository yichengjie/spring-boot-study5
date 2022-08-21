package com.yicj.view;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MultiViewApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MultiViewApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args) ;
    }
}
