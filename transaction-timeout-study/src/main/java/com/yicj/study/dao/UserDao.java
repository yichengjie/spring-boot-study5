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
    /*使用SqlSessionDaoSupport必须注意，此处源码1.1.1中有自动注入，1.2中取消了自动注入，需要手工注入，侵入性强
　　也可在spring-mybatis.xml中如下配置，但是这种有多少个dao就要配置到少个，多个dao就很麻烦。
    <bean id="userDao" class="com.hua.saf.dao.UserDao">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>*/
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
