package com.yicj.study.service.decorate;

import com.yicj.study.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceDecorate implements UserService {

    private UserService userService ;

    public UserServiceDecorate(UserService userService){
        this.userService = userService ;
    }

    @Override
    public void save() {
        log.info("--- decorate user service  ...");
        userService.save();
    }
}
