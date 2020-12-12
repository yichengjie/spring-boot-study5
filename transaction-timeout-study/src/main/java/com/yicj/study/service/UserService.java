package com.yicj.study.service;

import com.yicj.study.common.MyKeyList;
import com.yicj.study.entity.User;

public interface UserService {

    User selectById(Integer id) ;

    User select4Login(String username, String password) ;

    int insert(User user) ;

    MyKeyList<User> selectByUserItemResultMapper() ;
}
