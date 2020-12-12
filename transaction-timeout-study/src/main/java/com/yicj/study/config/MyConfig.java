package com.yicj.study.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@PropertySource("classpath:/jdbc.properties")
@Configuration
//@EnableConfigurationProperties(JdbcProperties.class)
public class MyConfig {
    //@Autowired
    //private JdbcProperties jdbcProperties ;
    /*@Value("${jdbc.driverClassName}")
    private String driverClassName ;
    @Value("${jdbc.url}")
    private String url ;
    @Value("${jdbc.username}")
    private String username ;
    @Value("${jdbc.password}")
    private String password ;
    @Value("${jdbc.initialSize}")
    private int initialSize ;
    @Value("${jdbc.maxIdle}")
    private Integer maxIdle ;
    @Value("${jdbc.minIdle}")
    private int minIdle ;
    @Value("${jdbc.maxWait}")
    private int maxWait ;*/

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource() ;
        return dataSource ;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean() ;
        sqlSessionFactory.setDataSource(dataSource());
        Resource configLocation = new ClassPathResource("mybatis-config.xml") ;
        sqlSessionFactory.setConfigLocation(configLocation);
        return sqlSessionFactory ;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager() ;
        transactionManager.setDataSource(dataSource());
        return transactionManager ;
    }
}
