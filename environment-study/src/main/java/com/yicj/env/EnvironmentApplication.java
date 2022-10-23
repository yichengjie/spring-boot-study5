package com.yicj.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import java.util.List;

@Slf4j
@SpringBootApplication
public class EnvironmentApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(EnvironmentApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
        List<String> packages = AutoConfigurationPackages.get(applicationContext);
        System.err.println("要扫描的包是packages: " + packages);
        Environment env = applicationContext.getEnvironment();
        System.out.println("- connection Name1111: " + env.getProperty("app.name"));
        System.out.println("- connection Url111: " + env.getProperty("app.url"));
    }
}
