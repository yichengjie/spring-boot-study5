package com.yicj.study.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    private String driverClassName ;
    private String url ;
    private String username ;
    private String password ;
    private int initialSize ;
    private Integer maxIdle ;
    private int minIdle ;
}
