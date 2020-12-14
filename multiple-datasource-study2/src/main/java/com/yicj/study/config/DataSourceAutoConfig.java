package com.yicj.study.config;

import com.yicj.study.common.datasource.DataSourceType;
import com.yicj.study.common.datasource.ThreadLocalVariableRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.*;


@Slf4j
@Configuration
public class DataSourceAutoConfig implements EnvironmentAware, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private Environment env ;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment ;
    }


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
     * 将 DataSourceGroupNameEnum 数据源组 生成bean对象注入到spring管理容器中；
     * 通过 datasource_druid_enabled 对应模块数据库配置总开关，默认值为true
     * 通过 datasource_druid_slave_enabled 对应模块从数据库配置总开关，默认值为true
     * 将模块初始化后数据源信息，并将结果生成spring bean 名称缓存到DataSourceGroupNameEnum对象中
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //this.registryDatasoruce(registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
