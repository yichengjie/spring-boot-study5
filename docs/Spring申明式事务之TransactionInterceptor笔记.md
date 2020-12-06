1. 使用ProxyFactoryBean + TransactionInterceptor添加申明式事务
    ```java
    @Configuration
    public class MyConfig {
        @Autowired
        private DataSourceProperties dataSourceProperties ;
        @Bean
        public DataSource dataSource(){
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(dataSourceProperties.getUrl());
            basicDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
            basicDataSource.setUsername(dataSourceProperties.getUsername());
            basicDataSource.setPassword(dataSourceProperties.getPassword());
            return basicDataSource ;
        }
        @Bean
        public JdbcTemplate jdbcTemplate(){
            JdbcTemplate jdbcTemplate = new JdbcTemplate() ;
            jdbcTemplate.setDataSource(dataSource());
            return jdbcTemplate ;
        }
        @Bean
        public PlatformTransactionManager transactionManager(){
            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager() ;
            transactionManager.setDataSource(dataSource());
            return transactionManager ;
        }
        @Bean
        public TransactionInterceptor transactionInterceptor(){
            TransactionInterceptor transactionInterceptor = new TransactionInterceptor() ;
            transactionInterceptor.setTransactionManager(transactionManager());
            Properties properties = new Properties();
            properties.setProperty("getQuote*","PROPAGATION_SUPPORTS,readOnly,timeout_20") ;
            properties.setProperty("saveQuote", "PROPAGATION_REQUIRED") ;
            properties.setProperty("updateQuote","PROPAGATION_REQUIRED") ;
            properties.setProperty("deleteQuote","PROPAGATION_REQUIRED") ;
            transactionInterceptor.setTransactionAttributes(properties);
            return transactionInterceptor ;
        }
        @Bean
        public IQuoteService quoteServiceTarget(JdbcTemplate jdbcTemplate){
            return new QuoteServiceImpl(jdbcTemplate);
        }
        @Bean(name = "quoteService")
        public ProxyFactoryBean quoteService(JdbcTemplate jdbcTemplate) throws ClassNotFoundException {
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean() ;
            proxyFactoryBean.setTarget(quoteServiceTarget(jdbcTemplate));
            Class<?>[] proxyInterfaces = new Class<?>[]{IQuoteService.class} ;
            proxyFactoryBean.setProxyInterfaces(proxyInterfaces);
            proxyFactoryBean.setInterceptorNames("transactionInterceptor");
            return proxyFactoryBean ;
        }
    }
    ```
2. 编写业务方法
    ```java
    @Data
    public class Quote {
        private Integer id ;
        private String name ;
    }
    public interface IQuoteService {
        Quote getQuote() ;
    }
    public class QuoteServiceImpl implements IQuoteService {
        private final JdbcTemplate jdbcTemplate ;
        public QuoteServiceImpl(JdbcTemplate jdbcTemplate){
            this.jdbcTemplate = jdbcTemplate ;
        }
        @Override
        public Quote getQuote() {
            String sql = "select * from quota where id =1" ;
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Quote quota = new Quote() ;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                quota.setId(id);
                quota.setName(name);
                return quota ;
            }) ;
        }
    }
    ```
3. 启动类排除掉TransactionAutoConfiguration类的自动注入（配置类中已手动添加事务相关配置）
    ```java
    @SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
    public class TransactionApplication {
        public static void main(String[] args) {
            SpringApplication.run(TransactionApplication.class, args) ;
        }
    }
    ```
4. 添加数据源配置
    ```properties
    # datasource 配置
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
    spring.datasource.username=root
    spring.datasource.password=root
    ```
