package com.yicj.study.beanwrapper.factorybean;

import org.springframework.beans.factory.FactoryBean;


public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setName("hello");
        return user;
    }
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}