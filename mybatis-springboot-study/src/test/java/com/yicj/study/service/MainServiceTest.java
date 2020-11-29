package com.yicj.study.service;


import com.yicj.study.MybatisApp;
import com.yicj.study.entity.SysRole;
import com.yicj.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApp.class)
public class MainServiceTest {
    @Autowired
    private MainService mainService ;

    @Test
    public void save(){
        User user = User.builder()
                .username("张三")
                .password("123")
                .roles("test")
                .build() ;
        SysRole sysRole = SysRole.builder()
                .name("test")
                .build() ;
        mainService.save(user, sysRole);
    }

}
