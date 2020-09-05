package com.yicj.study.service.impl;

import com.yicj.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        log.info("UserServiceImpl save user...");
    }
}
