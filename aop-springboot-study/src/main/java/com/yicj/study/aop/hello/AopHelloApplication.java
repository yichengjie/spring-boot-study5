package com.yicj.study.aop.hello;

import com.yicj.study.aop.hello.component.ApplicationContextUtil;
import com.yicj.study.aop.hello.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopHelloApplication implements ApplicationRunner{

    public static void main(String[] args) {
        SpringApplication.run(AopHelloApplication.class, args) ;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserService bean = ApplicationContextUtil.getBean(UserService.class);
        bean.saveUser("张三");
    }

}
