package com.yicj.study.dao;

import com.yicj.study.DynamicDatasourceApp;
import com.yicj.study.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApp.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao ;

    @Test
    public void select4Login(){
        String username = "yicj" ;
        String password = "123" ;
        User user = userDao.select4Login(username, password);
        log.info("======> {}", user);
        //wo
    }

    @Test
    public void select4Login2(){
        String username = "yicj" ;
        String password = "123" ;
        User user = userDao.select4Login2(username, password);
        log.info("======> {}", user);
    }

}
