package com.yicj.study.service;

import com.yicj.study.common.MyKeyList;
import com.yicj.study.entity.User;
import com.yicj.study.vo.LoginParam;

import java.util.List;

public interface UserService {

    User selectById(Integer id) ;

    List<User> select4Login(LoginParam loginParam) ;

    int insert(User user) ;

    MyKeyList<User> selectByUserItemResultMapper() ;
}
