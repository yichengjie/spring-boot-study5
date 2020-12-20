package com.yicj.study.aop.hello.service.impl;

import com.yicj.study.aop.hello.service.RoleService;
import com.yicj.study.aop.hello.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleService roleService ;

    @Override
    public void saveUser(String username) {

        log.info("save user {}", username);


    }
}
