package com.yicj.study.postprocessor.hello;

import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

// 可以添加多个数据源对象
@Configuration
@ConditionalOnProperty(name = "jdbc.driverClassName",  matchIfMissing = false)
public class DataSourceAutoConfig implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private Environment env ;
    private JdbcDataProperties jdbcDataProperties ;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinitionBuilder datasourceFactory = initDatasourceBean(jdbcDataProperties);
        BeanDefinition beanDefinition =  datasourceFactory.getBeanDefinition();
        registry.registerBeanDefinition("dataSource", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("====================> ");
    }

    /**
     * 初始化DruidDataSource对象
     * 通过BeanDefinitionBuilder生成DruidDataSource对象实现类
     * 并且通过配置文件获取对应的指定属性
     */
    private BeanDefinitionBuilder initDatasourceBean(JdbcDataProperties jdbcDataProperties){
        BeanDefinitionBuilder datasourceFactory = BeanDefinitionBuilder.genericBeanDefinition(BasicDataSource.class);
        datasourceFactory.setLazyInit(true);          //设置是否懒加载
        datasourceFactory.setScope(BeanDefinition.SCOPE_SINGLETON);       //设置scope,为单例类型
        datasourceFactory.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);  //设置是否可以被其他对象自动注入

        datasourceFactory.addPropertyValue("driverClassName", jdbcDataProperties.getDriverClassName());
        datasourceFactory.addPropertyValue("url", jdbcDataProperties.getUrl());
        datasourceFactory.addPropertyValue("username", jdbcDataProperties.getUsername());
        datasourceFactory.addPropertyValue("password", jdbcDataProperties.getPassword()) ;
        return datasourceFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment ;
        this.jdbcDataProperties = new JdbcDataProperties() ;
    }


    @Data
    private class JdbcDataProperties {
        private String driverClassName;
        private String url;
        private String username;
        private String password;


        public JdbcDataProperties(){
            this.setDriverClassName(env.getProperty("jdbc.driverClassName"));
            this.setUrl(env.getProperty("jdbc.url"));
            this.setUsername(env.getProperty("jdbc.username"));
            this.setPassword(env.getProperty("jdbc.password"));
        }
    }
}
