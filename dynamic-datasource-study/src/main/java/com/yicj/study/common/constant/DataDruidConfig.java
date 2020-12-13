package com.yicj.study.common.constant;

/**
 * DruidDataSource 数据库连接管理对象的相关参数属性配置对象；
 * 数据库连接池的相结合配置文件类型定义参数
 */
public interface DataDruidConfig {
    /**
     * DruidDataSource 对应属性的配置文件参数；可对应变更和修改
     * 根据自己的项目配置文件规则，只需要调整***PREFIX对应的参数即可；
     */
    String DATASOURCE_DRUID_PREFIX = "suven.datasource.druid.";

    /**
     * 数据库对象的模块的url,username,password的前缀
     * 根据自己的项目配置文件规则，只需要调整***PREFIX对应的参数即可；
     * suven.datasource.%s.%s.url        //eg: suven.datasource.assets.master.url
     * suven.datasource.%s.%s.username   //eg: suven.datasource.assets.master.username
     * suven.datasource.%s.%s.password;  //eg: suven.datasource.assets.master.password
     */
    String DATASOURCE_MODULE_PREFIX = "suven.datasource.";
    String DATASOURCDE_DRUID_FORMAT = "%s.%s.";
    /**
     * 通过 BeanDefinitionBuilder类，初始化DruidDataSource对应的属性参考
     */
    String URL                                        = "url";
    String USERNAME                                   = "username";
    String PASSWORD                                  = "password";
    String DRIVER_CLASSNAME                            = "driverClassName";
    String INITIALIZE                                = "initialize";
    String DBTYPE                                     = "dbType";
    String MAXACTIVE                                = "maxActive";
    String INITIALSIZE                              = "initialSize";
    String MAXWAIT                                   = "maxWait";
    String MINIDLE                                   = "minIdle";
    String TIME_BETWEENE_VICTION_RUNS_MILLIS             = "timeBetweenEvictionRunsMillis";
    String MIN_EVICTABLE_IDLE_TIME_MILLIS                 = "minEvictableIdleTimeMillis";
    String VALIDATION_QUERY                           = "validationQuery";
    String TEST_WHILEIDLE                            = "testWhileIdle";
    String TEST_ON_BORROW                             = "testOnBorrow";
    String  TEST_ON_RETURN                             = "testOnReturn";
    String POOL_PREPARED_STATEMENTS                      = "poolPreparedStatements";
    String CONNECTION_PROPERTIES                        =    "connectionProperties";
    String FILTERS                                     = "filters";

    String ENABLED                                     = "enabled";

    String datasource_druid_master                    = "master";
    String datasource_druid_slave                     ="slave";
    String datasource_druid_frame                     ="druid";
    String datasource_master_name                     = "MasterDataSource";
    String datasource_slave_name                      =  "SlaveDataSource";
    String datasource_param_config_enabled            = "config.enabled";

    /**
     * 初化所有数据源DataSourceAutoConfiguration类配置开关，默认为falase
     * suven.datasource.druid.frame.enabled=true
     */
    String datasource_druid_config_enabled                     =   DATASOURCE_DRUID_PREFIX +  datasource_param_config_enabled ;//"suven.datasource.druid.frame.enabled";
    /**
     * 初化指定模块数据源DataSourceGroupNameEnum类配置开关，默认为 true
     * suven.datasource.user.master.enabled=true
     */
    String datasource_druid_master_enabled  =                    DATASOURCE_MODULE_PREFIX +  DATASOURCDE_DRUID_FORMAT + ENABLED;//   "suven.datasource.%s.%s.enabled";
    /**
     * 初化所有数据源DataSourceAutoConfiguration类配置 从数据库的总开关，
     * 对应数据库的从数据库集合开关；默认为 true,
     * 若存在对应的从数据库的配置会自动加载，若需要关闭可以将该配置设计为false,或删除对应配置
     * suven.datasource.druid.user.slave.enabled=true
     */
    String datasource_druid_slave_enabled =                           DATASOURCE_MODULE_PREFIX + DATASOURCDE_DRUID_FORMAT + ENABLED;//    "suven.datasource.druid.%s.slave.enabled";

    String datasource_druid_url =                                        DATASOURCE_MODULE_PREFIX + DATASOURCDE_DRUID_FORMAT + URL;//    "suven.datasource.%s.%s.url";// = jdbc:mysql://112.124.49.40/datasource_name?autoReconnect=true&characterEncoding=utf-8
    String datasource_druid_username =                                   DATASOURCE_MODULE_PREFIX +  DATASOURCDE_DRUID_FORMAT + USERNAME;//   "suven.datasource.%s.%s.username";//suven.datasource.assets.master.username = redfinger
    String datasource_druid_password =                                   DATASOURCE_MODULE_PREFIX + DATASOURCDE_DRUID_FORMAT +  PASSWORD;//    "suven.datasource.%s.%s.password";// suven.datasource.assets.master.password = redfinger


    String datasource_druid_initialSize =                            DATASOURCE_DRUID_PREFIX + INITIALSIZE;// "suven.datasource.druid.initialSize";
    String datasource_druid_minIdle =                                 DATASOURCE_DRUID_PREFIX + MINIDLE;//"suven.datasource.druid.minIdle";
    String datasource_druid_maxActive =                              DATASOURCE_DRUID_PREFIX + MAXACTIVE;// "suven.datasource.druid.maxActive";
    String datasource_druid_maxWait =                                DATASOURCE_DRUID_PREFIX + MAXWAIT;// "suven.datasource.druid.maxWait";
    String datasource_druid_timeBetweenEvictionRunsMillis =          DATASOURCE_DRUID_PREFIX + TIME_BETWEENE_VICTION_RUNS_MILLIS;//  "suven.datasource.druid.timeBetweenEvictionRunsMillis";
    String datasource_druid_minEvictableIdleTimeMillis =              DATASOURCE_DRUID_PREFIX + MIN_EVICTABLE_IDLE_TIME_MILLIS;// "suven.datasource.druid.minEvictableIdleTimeMillis";
    String datasource_druid_validationQuery =                         DATASOURCE_DRUID_PREFIX + VALIDATION_QUERY;// "suven.datasource.druid.validationQuery";
    String datasource_druid_testWhileIdle =                              DATASOURCE_DRUID_PREFIX + TEST_WHILEIDLE;//  "suven.datasource.druid.testWhileIdle";
    String datasource_druid_testOnBorrow =                              DATASOURCE_DRUID_PREFIX + TEST_ON_BORROW;//  "suven.datasource.druid.testOnBorrow";
    String datasource_druid_testOnReturn =                             DATASOURCE_DRUID_PREFIX + TEST_ON_RETURN;//    "suven.datasource.druid.testOnReturn";
    String datasource_druid_poolPreparedStatements =                   DATASOURCE_DRUID_PREFIX + POOL_PREPARED_STATEMENTS;//    "suven.datasource.druid.poolPreparedStatements";
    String datasource_druid_filters =                                   DATASOURCE_DRUID_PREFIX + FILTERS;//    "suven.datasource.druid.filters";
    String datasource_druid_connectionProperties =                       DATASOURCE_DRUID_PREFIX + CONNECTION_PROPERTIES;//    "suven.datasource.druid.connectionProperties";
}
