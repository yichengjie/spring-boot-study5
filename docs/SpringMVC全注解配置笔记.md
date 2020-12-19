1. 编写根容器配置类
    ```java
    @Configuration
    @ComponentScan("com.yicj.study.mvc.hello.service")
    public class RootConfig {
    }
    ```
2. 编写web容器配置类
    ```java
    @EnableWebMvc
    @Configuration
    @ComponentScan("com.yicj.study.mvc.hello.controller")
    public class WebConfig {
        @Bean
        public InternalResourceViewResolver viewResolver(){
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver() ;
            viewResolver.setPrefix("/WEB-INF/jsp/");
            viewResolver.setSuffix(".jsp");
            return viewResolver ;
        }
    }
    ```
3. 编写WebApplicationInitializer实现类,一般直接继承AbstractAnnotationConfigDispatcherServletInitializer类
    ```java
    // SpringServletContainerInitializer实现了ServletContainerInitializer接口
    // 在SpringServletContainerInitializer的onStartup方法中会
    public class StarterWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        // 根容器的配置类
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return new Class[]{RootConfig.class};
        }
        // web容器的配置类
        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[]{WebConfig.class};
        }
        // DispatcherServlet拦截地址
        @Override
        protected String[] getServletMappings() {
            return new String[]{"/"};
        }
        // 指定DispatcherServlet中的filter
        @Override
        protected Filter[] getServletFilters() {
            CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true) ;
            return new Filter[]{encodingFilter};
        }
    }
    ```
4. 编写业务代码，以及控制层代码
    ```java
    @Data
    public class User {
        private Integer id ;
        private String name ;
    }
    //
    @Service
    public class UserServiceImpl implements UserService {
        @Override
        public User saveUser(User user) {
            user.setId(1);
            return user ;
        }
    }
    //
    @Controller
    public class HomeController {
        @ResponseBody
        @GetMapping("/hello")
        public String hello(){
            return "hello world" ;
        }
    
        @GetMapping("/helloworld")
        public String helloworld(){
    
            return "helloworld" ;
        }
    }
    ```
5. 在webapp/WEB-INF/jsp/ 目录中创建helloworld.jsp
    ```jsp
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <title>hello test</title>
    </head>
    <body>
        Hello World!<br/>
    </body>
    </html>
    ```
6. 添加依赖
    ```xml
    <!-- 注意这里要写war否则项目可能无法运行-->
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>provided</scope>
        </dependency>
    </dependencies>
    ```
7. Servlet3.x规范利用spi机制，自动加载实现ServletContainerInitializer接口的类,并调用onStartup方法。
    ```txt
    a) SpringServletContainerInitializer实现ServletContainerInitializer接口。
    b) SpringServletContainerInitializer添加@HandlesTypes(WebApplicationInitializer.class)注解,表示获取所有WebApplicationInitializer实现类。
    c) 在SpringServletContainerInitializer的onStartup方法中调用WebApplicationInitializer的onStartup方法，
    ```
      
