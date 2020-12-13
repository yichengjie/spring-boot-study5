package com.yicj.study.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.yicj.study.model.datasource.DataChooseParam;
import com.yicj.study.model.datasource.DataSourceEnum;
import com.yicj.study.model.datasource.DataSourceGroup;
import com.yicj.study.model.datasource.DataSourceGroupNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 将 DataSourceGroupNameEnum 数据源组 生成bean对象注入到spring管理容器中；
 * 通过 datasource_druid_enabled 对应模块数据库配置总开关，默认值为true
 * 通过 datasource_druid_slave_enabled 对应模块从数据库配置总开关，默认值为true
 * 将模块初始化后数据源信息，并将结果生成spring bean 名称缓存到DataSourceGroupNameEnum对象中
 * 将所有模块通过initDataByGroup 初始化后的数据库的聚群，初始化到目标的动态数据池子里；
 * 再根据模块名称字符串 作为模块key 从targetDataSources 的Map中获取指定模块的数据库的连接池；
 * 从而通过动态桥接的设计模式来达到数据源连接池动态切换原理；
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private boolean isDefaultTargetDataSource = true;
    private Map<Object, Object> targetDataSources = new HashMap<>();
    private ConcurrentHashMap<String, AtomicInteger> currentMap = new ConcurrentHashMap<>();

    /**
     * 将所有模块通过initDataByGroup 初始化后的数据库的聚群，初始化到目标的动态数据池子里；
     */
    public void setTargetDataSources(){
        super.setTargetDataSources(targetDataSources);
    }


    public void setTargetDataSources(Map<Object, Object> targetDataSources){
        Object defaultTargetDataSource = targetDataSources.entrySet().iterator().next();
        if(isDefaultTargetDataSource){
            this.setDefaultTargetDataSource(defaultTargetDataSource);
            isDefaultTargetDataSource =false;
        }
        this.targetDataSources.putAll(targetDataSources);
        this.setTargetDataSources();
    }



    /**
     * DataSourceGroupNameEnum 枚举类型的实现初始化指定模块数据源信息；
     * @param dataSourceGroupNameEnum
     */
    public void initDataByGroup(DataSourceGroupNameEnum dataSourceGroupNameEnum, ApplicationContext applicationContext){
        Map<Object, Object> targetDataSources = new HashMap<>();

        DataSourceGroup group = DataSourceGroupNameEnum.getDataSourceGroupByName(dataSourceGroupNameEnum);
        if(null == group || null == group.getMasterSources()){
            return;
        }
        String master = group.getMasterSources();
        DataSource defaultTargetDataSource = applicationContext.getBean(master, DruidDataSource.class);
        targetDataSources.put(master, defaultTargetDataSource);
        List<String> list = group.getSlaveSources();
        if(null != list && !list.isEmpty()){
            for (String slave : list){
                try{
                    DataSource datasource = applicationContext.getBean(slave,DruidDataSource.class);
                    if(null == datasource){
                        continue;
                    }
                    targetDataSources.put(slave,datasource );
                }catch (Exception e){
                    log.info("初始化[{}] slave 数据源失败,检查slave开关是否开启!", slave);
                }
            }
        }
        if(isDefaultTargetDataSource){
            this.setDefaultTargetDataSource(defaultTargetDataSource);
        }

        this.targetDataSources.putAll(targetDataSources);

    }

    // 获取数据源名称,采用轮询的方式实现
    @Override
    protected Object determineCurrentLookupKey() {
        DataChooseParam dataSource = DataSourceHolder.getDataSource();
        DataSourceHolder.clear();
        if (null == dataSource) {
            throw new IllegalArgumentException("Property 'determineCurrentLookupKey' is DataSourceHolder.getDataSource() not  dataSource:[" + dataSource + "]");

        }
        DataSourceGroup dataSourceGroup = DataSourceGroupNameEnum.getDataSourceGroupByName(dataSource.getGroupName());

        if (null == dataSourceGroup) {
            throw new IllegalArgumentException("Property 'determineCurrentLookupKey' is DataSourceGroupNameEnum.getDataSourceGroupByName() not  dataSourceGroup:[" + dataSourceGroup + "]");
        }
        String dataSourceKey = null;
//		Map<String,List<String>>  map = dataSourceGroupMap.get(dataSource.getGroupName());
        DataSourceEnum dataEnum = dataSource.getDataType();
        if (null == dataEnum) {
            dataEnum = DataSourceEnum.MASTER;
        }

        if (DataSourceEnum.MASTER == dataSource.getDataType()) {
            dataSourceKey = dataSourceGroup.getMasterSources();
            dataSource.setDataClient(dataSourceKey);
            return dataSourceKey;
        }
        if (DataSourceEnum.SLAVE == dataSource.getDataType()) {
            List<String> list = dataSourceGroup.getSlaveSources();
            if (null == list || list.isEmpty()) {
                dataSourceKey = dataSourceGroup.getMasterSources();
                dataSource.setDataClient(dataSourceKey);
                return dataSourceKey;
            }
//			throw new IllegalArgumentException("Property 'determineCurrentLookupKey' is DataSourceHolder.map.get(dataType) list isEmpty or null ");
            int size = list.size();
            if (size == 1) {
                dataSourceKey = list.get(0);
            } else {
                AtomicInteger counter = currentMap.get(dataEnum.name());
                if (counter == null) {
                    counter = new AtomicInteger(0);
                    currentMap.put(dataEnum.name(), counter);
                } else {
                    if (counter.incrementAndGet() >= size) {
                        counter.set(0);
                    }
                }
                dataSourceKey = list.get(counter.intValue());
            }
        }
        dataSource.setDataClient(dataSourceKey);
        return dataSourceKey;

    }
}
