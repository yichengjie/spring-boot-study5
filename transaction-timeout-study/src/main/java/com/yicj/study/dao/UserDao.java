package com.yicj.study.dao;

import com.yicj.study.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao extends SqlSessionDaoSupport {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate ;

    public User select4Login(String username, String password){
        User param = new User() ;
        param.setUsername(username);
        param.setPassword(password);
        User entity = sqlSessionTemplate.selectOne("com.yicj.study.mapper.UserMapper.select4Login", param);
        return entity ;
    }

    public User select4Login2(String username, String password){
        User param = new User() ;
        param.setUsername(username);
        param.setPassword(password);
        User entity = this.getSqlSession().selectOne("com.yicj.study.mapper.UserMapper.select4Login", param);
        return entity ;
    }

    // 注入SqlSessionFactory对象
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
