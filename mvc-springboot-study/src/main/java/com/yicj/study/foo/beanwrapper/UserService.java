package com.yicj.study.foo.beanwrapper;

import lombok.Data;

@Data
public class UserService {
    private UserDao userDao ;
    private RoleDao roleDao ;
}
