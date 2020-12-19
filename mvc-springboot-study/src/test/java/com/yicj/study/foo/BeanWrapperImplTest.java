package com.yicj.study.foo;

import com.yicj.study.foo.beanwrapper.RoleDao;
import com.yicj.study.foo.beanwrapper.UserDao;
import com.yicj.study.foo.beanwrapper.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanWrapperImplTest {

    @Test
    public void test1(){
        UserDao userDao = new UserDao() ;
        RoleDao roleDao = new RoleDao() ;
        UserService userService = new UserService() ;

        BeanWrapper newService = new BeanWrapperImpl(userService) ;
        newService.setPropertyValue("userDao", userDao);
        newService.setPropertyValue("roleDao", roleDao);

        Assert.assertTrue(newService.getWrappedInstance() instanceof UserService);
        Assert.assertSame(userService, newService.getWrappedInstance());
        Assert.assertSame(userDao, newService.getPropertyValue("userDao"));
        Assert.assertSame(roleDao, newService.getPropertyValue("roleDao"));
    }
}
