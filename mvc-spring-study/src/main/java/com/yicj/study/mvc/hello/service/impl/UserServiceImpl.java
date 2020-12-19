package com.yicj.study.mvc.hello.service.impl;

import com.yicj.study.mvc.hello.entity.User;
import com.yicj.study.mvc.hello.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        user.setId(1);
        return user ;
    }
}
