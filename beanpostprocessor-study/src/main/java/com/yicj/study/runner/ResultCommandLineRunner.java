package com.yicj.study.runner;

import com.yicj.study.component.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ResultCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserService userService ;

    @Override
    public void run(String... args) throws Exception {

        userService.hello();
    }
}