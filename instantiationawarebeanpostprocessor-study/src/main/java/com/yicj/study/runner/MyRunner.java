package com.yicj.study.runner;

import com.yicj.study.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext ;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        UserService userService = applicationContext.getBean(UserService.class);
        userService.saveUser();

        System.out.println("hello....");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext ;
    }
}
