package com.yicj.study.service;

import com.yicj.study.entity.User;

import java.util.List;

public interface UserService {
    int insert(User user) ;

    List<User> selectAll() ;

    User select4Login(String username, String password) ;
}
