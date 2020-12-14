package com.yicj.study.config;

import com.yicj.study.common.datasource.DataSourceType;
import com.yicj.study.common.datasource.ThreadLocalVariableRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
public class DataSourceAutoConfig{

    @Bean("mainDataSource")
    @ConfigurationProperties(prefix = "jdbc.main")
    public DataSource mainDataSource() {
        BasicDataSource dataSource = new BasicDataSource() ;
        return dataSource;
    }

    @Bean("infoDataSource")
    @ConfigurationProperties(prefix = "jdbc.info")
    public DataSource infoDataSource() {
        BasicDataSource dataSource = new BasicDataSource() ;
        return dataSource;
    }

    @Bean("dataSource")
    public DataSource dataSource(){
        ThreadLocalVariableRoutingDataSource dataSource = new ThreadLocalVariableRoutingDataSource() ;
        dataSource.setDefaultTargetDataSource(mainDataSource());
        Map<Object, Object> targetDataSources = new HashMap<>() ;
        targetDataSources.put(DataSourceType.MAIN, mainDataSource()) ;
        targetDataSources.put(DataSourceType.INFO, infoDataSource()) ;
        dataSource.setTargetDataSources(targetDataSources);
        return dataSource ;
    }

    /**
     * @AutoConfigureAfter({ DataSourceAutoConfiguration.class, MybatisLanguageDriverAutoConfiguration.class })
     * public class MybatisAutoConfiguration implements InitializingBean {
     * }
     * 注意MybatisAutoConfiguration注解上有@ConditionalOnSingleCandidate(DataSource.class)
     * 当超过一个dataSource时MybatisAutoConfiguration将不生效，所以需要我们自动配置sqlSessionFactory
     * @return
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean() ;
        Resource configLocation = new ClassPathResource("mybatis-config.xml") ;
        sqlSessionFactoryBean.setConfigLocation(configLocation);
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean ;
    }

}
