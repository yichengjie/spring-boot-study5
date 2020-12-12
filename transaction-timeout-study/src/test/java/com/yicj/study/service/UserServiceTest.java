package com.yicj.study.service;


import com.yicj.study.TransactionTimeoutApp;
import com.yicj.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionTimeoutApp.class)
public class UserServiceTest {

    @Autowired
    private UserService userService ;

    @Test
    public void save(){
        User user = User.builder()
                .username("李四")
                .password("123")
                .roles("test")
                .build() ;
        userService.insert(user) ;
    }

    @Test
    public void selectAll(){
        List<User> users = userService.selectAll();
        log.info("====> {}", users);
    }

    @Test
    public void select4Login(){
        String username = "yicj" ;
        String password = "123" ;
        User user = userService.select4Login(username, password);
        log.info("user : {}", user);
    }

}
