package com.yicj.study.config;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.yicj.study.common.constant.DataDruidConfig;
import com.yicj.study.common.datasource.DynamicDataSource;
import com.yicj.study.model.datasource.DataSourceGroupNameEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,JdbcTemplateAutoConfiguration.class})
@ConditionalOnProperty(name = DataDruidConfig.datasource_druid_config_enabled,  matchIfMissing = false)
public class DataSourceAutoConfig implements DataDruidConfig, EnvironmentAware, BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private Environment env ;
    private DruidDataConfig druidConfig;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment ;
        druidConfig = new DruidDataConfig();
        log.warn("DataSourceAutoConfiguration in DruidDataConfig info=[{}]", druidConfig.toString());
    }


    @Bean("dataSource")
    public DataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        Set<DataSourceGroupNameEnum> sourceNames = DataSourceGroupNameEnum.getSourceNames();
        if (sourceNames == null || sourceNames.isEmpty()) {
            throw  new RuntimeException("DynamicDataSource init DataSource isEmpty ");
        }
        for (DataSourceGroupNameEnum dataName : sourceNames) {
            dataSource.initDataByGroup(dataName, applicationContext);
        }
        dataSource.setTargetDataSources();
        log.warn("Dynamic DataSource Registry --- routingDataSource Successfully ...      ");
        return dataSource;
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


    private void registryDatasoruce(BeanDefinitionRegistry registry){
        Set<DataSourceGroupNameEnum> sourceNames = null;
        sourceNames = DataSourceGroupNameEnum.getSourceNames();
        if (sourceNames == null || sourceNames.isEmpty()) {
            return;
        }
        boolean isPrimary = true;
        //从数据库配置总开关，
        boolean dataSourceSlaveEnabled = env.getProperty(
                String.format(datasource_druid_slave_enabled,datasource_druid_frame,datasource_druid_slave).trim(),
                Boolean.class,true);

        for (DataSourceGroupNameEnum dataName : sourceNames) {
            //模块数据库 主-从 配置总开关，
            boolean moduleDataSourceEnabled = env.getProperty(
                    String.format(datasource_druid_master_enabled,dataName.getValue(),datasource_druid_master).trim(),
                    Boolean.class,true);

            //模块从数据库配置总开关，
            boolean moduleDataSourceSlaveEnabled = env.getProperty(
                    String.format(datasource_druid_slave_enabled,dataName.getValue(),datasource_druid_slave).trim(),
                    Boolean.class,true);
            /**
             * 通过模块对应的配置文件获取主数据库信息，如果不存在就跳该模块的对应的所有数据库
             */
            String url = env.getProperty(String.format(datasource_druid_url,dataName.getValue(),datasource_druid_master));
            if (null == url || "".equals(url) || !moduleDataSourceEnabled) {
                continue;
            }
            String username = env.getProperty(String.format(datasource_druid_username,dataName.getValue(),datasource_druid_master).trim());
            String password = env.getProperty(String.format(datasource_druid_password,dataName.getValue(),datasource_druid_master).trim());

            /**
             * 注入到spring bean的名称生成规则；（模块文称+ MasterDataSource）
             */
            String datasourceMasterBeanName = dataName.getValue() + datasource_master_name ;

            BeanDefinitionBuilder datasourceFactory = initDatasourceBean(druidConfig,url,username,password);
            BeanDefinition beanDefinition =  datasourceFactory.getBeanDefinition();
            if(isPrimary){//设置唯一主数据库
                beanDefinition.setPrimary(true);
                isPrimary = false;
            }
            registry.registerBeanDefinition(datasourceMasterBeanName, beanDefinition);
            List<String> slaveDataSources = new ArrayList<>();
            int i = 0 ;
            while (dataSourceSlaveEnabled && moduleDataSourceSlaveEnabled){
                String slave = i == 0 ? datasource_druid_slave : datasource_druid_slave + i;
                /**
                 * 注入到spring bean的名称生成规则；（模块文称+ SlaveDataSource + 序列号1,2,3...）
                 */
                String datasourceSlaveBeanName = dataName.getValue() + datasource_slave_name + i;
                url = env.getProperty(String.format(datasource_druid_url,dataName.getValue(),slave).trim());
                if (null == url || "".equals(url)) {
                    break;
                }
                username = env.getProperty(String.format(datasource_druid_username,dataName.getValue(),slave).trim());
                password = env.getProperty(String.format(datasource_druid_password,dataName.getValue(),slave).trim());
                datasourceFactory = initDatasourceBean(druidConfig,url,username,password);
                registry.registerBeanDefinition(datasourceSlaveBeanName, datasourceFactory.getBeanDefinition());
                slaveDataSources.add(datasourceSlaveBeanName);
                i++;
            }
            /**
             * 将模块初始化后数据源信息，并将结果生成spring bean 名称缓存到DataSourceGroupNameEnum 对象中
             */
            DataSourceGroupNameEnum.setDataSource(dataName,datasourceMasterBeanName,slaveDataSources);
            log.warn("DataSourceAutoConfig postProcessBeanDefinitionRegistry Registry --- dataSourceName[{}] Successfully ...",datasourceMasterBeanName);
        }
    }


    /**
     * 初始化DruidDataSource对象
     * 通过BeanDefinitionBuilder生成DruidDataSource对象实现类
     * 并且通过配置文件获取对应的指定属性
     * @param url
     * @param username
     * @param password
     * @return
     */
    private BeanDefinitionBuilder initDatasourceBean(DruidDataConfig druid, String url, String username, String password){
        BeanDefinitionBuilder datasourceFactory = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
        datasourceFactory.setLazyInit(true);          //设置是否懒加载
        datasourceFactory.setScope(BeanDefinition.SCOPE_SINGLETON);       //设置scope,为单例类型
        datasourceFactory.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);  //设置是否可以被其他对象自动注入

        datasourceFactory.addPropertyValue(URL, url);
        datasourceFactory.addPropertyValue(USERNAME, username);
        datasourceFactory.addPropertyValue(PASSWORD, password);
        initDataSource(datasourceFactory,druid);
        return datasourceFactory;
    }


//

    /**
     * 初始化数据库的默认配置参数；
     * @param datasourceFactory
     */
    private void initDataSource(BeanDefinitionBuilder datasourceFactory,DruidDataConfig druid){

        datasourceFactory.addPropertyValue(INITIALSIZE, druid.getInitialSize());
        datasourceFactory.addPropertyValue(MINIDLE, druid.getMinIdle());
        datasourceFactory.addPropertyValue(MAXACTIVE, druid.getMaxActive());
        datasourceFactory.addPropertyValue(MAXWAIT, druid.getMaxWait());
        datasourceFactory.addPropertyValue(TIME_BETWEENE_VICTION_RUNS_MILLIS,druid.getTimeBetweenEvictionRunsMillis() );
        datasourceFactory.addPropertyValue(MIN_EVICTABLE_IDLE_TIME_MILLIS, druid.getMinEvictableIdleTimeMillis() );
        datasourceFactory.addPropertyValue(VALIDATION_QUERY, druid.getValidationQuery());
        datasourceFactory.addPropertyValue(TEST_WHILEIDLE, druid.isTestWhileIdle());
        datasourceFactory.addPropertyValue(TEST_ON_BORROW, druid.isTestOnBorrow());
        datasourceFactory.addPropertyValue(TEST_ON_RETURN, druid.isTestOnReturn());
        datasourceFactory.addPropertyValue(POOL_PREPARED_STATEMENTS, druid.isPoolPreparedStatements());

        try {
            datasourceFactory.addPropertyValue(FILTERS,druid.getFilters());
        } catch (Exception e) {
            log.error("druid configuration initialization filter", e);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Data
    private class DruidDataConfig {
        private int initialSize;
        private int minIdle;
        private int maxActive;
        private int maxWait;
        private long timeBetweenEvictionRunsMillis;
        private long minEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private String filters;

        public DruidDataConfig(){
            this.setInitialSize(env.getProperty(datasource_druid_initialSize,Integer.class, DruidAbstractDataSource.DEFAULT_INITIAL_SIZE));
            this.setMinIdle(env.getProperty(datasource_druid_minIdle,Integer.class,DruidAbstractDataSource.DEFAULT_MIN_IDLE));
            this.setMaxActive(env.getProperty(datasource_druid_maxActive,Integer.class,DruidAbstractDataSource.DEFAULT_MAX_ACTIVE_SIZE));
            this.setMaxWait(env.getProperty(datasource_druid_maxWait,Integer.class,DruidAbstractDataSource.DEFAULT_MAX_WAIT));
            this.setTimeBetweenEvictionRunsMillis(env.getProperty(datasource_druid_timeBetweenEvictionRunsMillis,Long.class,DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS));
            this.setMinEvictableIdleTimeMillis(env.getProperty(datasource_druid_minEvictableIdleTimeMillis,Long.class,DruidAbstractDataSource.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS));
            this.setValidationQuery(env.getProperty(datasource_druid_validationQuery,DruidAbstractDataSource.DEFAULT_VALIDATION_QUERY));
            this.setTestWhileIdle(env.getProperty(datasource_druid_testWhileIdle,Boolean.class,true));
            this.setTestOnBorrow(env.getProperty(datasource_druid_testOnBorrow,Boolean.class));
            this.setTestOnReturn(env.getProperty(datasource_druid_testOnReturn,Boolean.class));
            this.setPoolPreparedStatements(env.getProperty(datasource_druid_poolPreparedStatements,Boolean.class));
            this.setFilters(env.getProperty(datasource_druid_filters,""));
        }
    }
}
