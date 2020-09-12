package com.yicj.study.importregistrar.hello2.config;

import com.yicj.study.importregistrar.hello2.EnableHello2ConfigurationProperties;
import com.yicj.study.importregistrar.hello2.service.Hello2Service;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHello2ConfigurationProperties({Hello2Service.class})
public class Hello2Configuration {

}
