1. 编写业务对象
    ```java
    public class RoleDao {}
    public class UserDao {}
    @Data
    public class UserService {
        private UserDao userDao ;
        private RoleDao roleDao ;
    }
    ```
2. 编写测代码
    ```java
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
    ```