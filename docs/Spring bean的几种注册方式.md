#### BeanDefinition
1. 实体类
    ```java
    @Data
    public class Student {
        private String name ;
        private String addr ;
    }
    ```
2. 配置类
    ```java
    @ComponentScan(value = "com.yicj.study.beanwrapper.beandefinition")
    public class Config {
    }
    ```
3. 测试类
    ```java
    @Slf4j
    public class BeanDefinitionApp {
        public static void main(String[] args) {
            // 首先获取容器上下文
            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext(Config.class) ;
            // 生成java类对应的BeanDefinitionBuilder
            BeanDefinitionBuilder builder =
                    BeanDefinitionBuilder.genericBeanDefinition(Student.class) ;
            //将BeanDefinition注册到该spring容器上
            context.registerBeanDefinition("student",builder.getBeanDefinition());
            // 尝试获取
            Object student = context.getBean("student");
            log.info("student : {}", student);
        }
    }
    ```
#### FactoryBean
1. 实体类
    ```java
    @Data
    public class User {
        private String name;
    }
    ```
2. FactoryBean实现类
    ```java
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
    ```
3. 测试类
    ```java
    public class FactoryBeanTest {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            BeanDefinitionBuilder builder = BeanDefinitionBuilder
                    .genericBeanDefinition(UserFactoryBean.class);
            context.refresh();
            context.registerBeanDefinition("user", builder.getBeanDefinition());
            System.out.println("获取user:"+context.getBean("user"));
            System.out.println("获取userFactoryBean:"+context.getBean("&user"));
        }
    }
    ```
4. 补充
    ```text
    FactoryBean在spring中的地位也是很高的。在mybatis框架中如何将Mapper接口
    注册到spring容器就是利用他的功能。因为spring中是无法注册接口的。mybatis将
    接口生成代理类注册到spring容器。在执行这些代理类的时候在根据Mapper对象的xml
    里的sql进行SqlSession执行sql进行数据的解析。
    ```
#### Supplier
1. 测试类
    ```java
    @Slf4j
    public class SupplierApp {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.refresh();
            context.registerBean(User.class,new Supplier<User>(){
                @Override
                public User get() {
                    User user = new User();
                    user.setName(new Date().toString());
                    return user;
                }
            } ,new BeanDefinitionCustomizer(){
                @Override
                public void customize(BeanDefinition bd) {
                    bd.setPrimary(true);
                    log.info("===> bd : {}", bd);
                }
            });
            User user = (User) context.getBean("user");
            log.info("===> user :{}",user.getName());
        }
    }
    ```

    