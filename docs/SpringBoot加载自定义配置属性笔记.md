###方式1通过@PropertySource注解读取配置文件,可以读取任何配置文件
1. 编写自定义配置文件jdbc.properties
    ```properties
    jdbc.driverClassName=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
    jdbc.username=root
    jdbc.password=root
    jdbc.initialSize=1
    jdbc.maxIdle=20
    jdbc.minIdle=3
    jdbc.maxWait=60000
    ```
2. 编写配置类读取配置
    ```java
    @Configuration
    @PropertySource("classpath:/jdbc.properties")
    public class MyConfig {
        private Environment env ;
        @Value("${jdbc.driverClassName}")
        private String driverClassName ;
        @Value("${jdbc.url}")
        private String url ;
        @Value("${jdbc.username}")
        private String username ;
        @Value("${jdbc.password}")
        private String password ;
        @Value("${jdbc.initialSize}")
        private String initialSize ;
        @Value("${jdbc.maxIdle}")
        private String maxIdle ;
        @Value("${jdbc.minIdle}")
        private String minIdle ;
        @Value("${jdbc.maxWait}")
        private String maxWait ;
        @Bean
        public DataSource dataSource(){
            BasicDataSource dataSource = new BasicDataSource() ;
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setInitialSize(initialSize);
            dataSource.setMaxIdle(maxIdle);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxWaitMillis(maxWait);
            return dataSource ;
        }
     }
    ```
#### 方式2通过通过application.properties文件读取配置信息
1. 在application.properties文件中加入自定义配置
    ```properties
    jdbc.driverClassName=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
    jdbc.username=root
    jdbc.password=root
    jdbc.initialSize=1
    jdbc.maxIdle=20
    jdbc.minIdle=3
    jdbc.maxWait=60000
    ```
2. 编写配置对应的java对象,并添加@ConfigurationProperties注解
    ```java
    @Data
    @ConfigurationProperties(prefix = "jdbc")
    public class JdbcProperties {
        private String driverClassName ;
        private String url ;
        private String username ;
        private String password ;
        private int initialSize ;
        private Integer maxIdle ;
        private int minIdle ;
    }
    ```
3. 在配置属性上添加@EnableConfigurationProperties导入JdbcProperties.class
    ```java
    @Configuration
    @EnableConfigurationProperties(JdbcProperties.class)
    public class MyConfig {
        
    }
    ```
4. 在需要使用的地方直接使用@Autowired注入即可
    ```text
    @Autowired
    private JdbcProperties jdbcProperties ;
    ```
### 方式3直接实例化Bean的地方使用@ConfigurationProperties(prefix = "jdbc")
1. 在application.properties文件中加入自定义配置
    ```properties
    jdbc.driverClassName=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
    jdbc.username=root
    jdbc.password=root
    jdbc.initialSize=1
    jdbc.maxIdle=20
    jdbc.minIdle=3
    jdbc.maxWait=60000
    ```
2. 在实例化Bean的地方添加注解(其实这种本质上与方式2一样，但是不需要使用@EnableConfigurationProperties单独导入)
    ```java
    @Configuration
    public class MyConfig {
        // 这里会根据配置文件的内容,调用对象的set方法
        @Bean
        @ConfigurationProperties(prefix = "jdbc")
        public DataSource dataSource(){
            BasicDataSource dataSource = new BasicDataSource() ;
            return dataSource ;
        }
    }
    ```




