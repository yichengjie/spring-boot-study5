package com.yicj.study.dao;

import com.yicj.study.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate ;

    public User select4Login(String username, String password){
        User param = new User() ;
        param.setUsername(username);
        param.setPassword(password);
        User entity = sqlSessionTemplate.selectOne("com.yicj.study.mapper.UserMapper.select4Login", param);
        return entity ;
    }
}
