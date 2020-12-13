package com.yicj.study.model.datasource;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目数据源名称泛型实现类，
 * 用来实现多数据源自动切换使用，配合 DataSourceAutoConfiguration 启动初化数据源注入到spring bean 中
 * 创建spring DynamicDataSource bean 对象，并注入到spring容器中，命名为dataSource;
 */
@Slf4j
public enum DataSourceGroupNameEnum {
    DATA_NAME_USER("user"),//用户组数据库,包括主和从数据库
    DATA_NAME_OAUTH("oauth"),//验证组数据库,包括主和从数据库
    DATA_NAME_ASSETS("assets"),//用户资产组数据库,包括主和从数据库
    // 随着项目和数据的增加多,在型这里增加属性即可
    ;
    private String value;
    private static Map<String, DataSourceGroupNameEnum> tbTypeMap = new LinkedHashMap<>();
    private static Map<String,DataSourceGroup> groupMap = new ConcurrentHashMap<>();

    static {
        for(DataSourceGroupNameEnum type : values()) {
            tbTypeMap.put(type.name(), type);
            groupMap.put(type.name(), new DataSourceGroup().setGroupName(type.name()));
        }
    }


    DataSourceGroupNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static DataSourceGroupNameEnum getName(String name){
        return tbTypeMap.get(name);
    }

    public static Collection<DataSourceGroupNameEnum> getValues(){
        return tbTypeMap.values();
    }

    public static Set<DataSourceGroupNameEnum> getSourceNames(){
        Set<DataSourceGroupNameEnum> sourceNames = new LinkedHashSet<>();
        sourceNames.addAll(tbTypeMap.values());
        return sourceNames;
    }


    //通过组名，获取数据源集合对象；
    public static DataSourceGroup getDataSourceGroupByName(String groupName){
        if(null == groupName ){
            log.warn("DataSourceGroup getDataSourceGroupByName by groupName[{}]", groupName);
            return null;
        }
        DataSourceGroupNameEnum dataSourceGroupNameEnum = getName(groupName);

        return getDataSourceGroupByName(dataSourceGroupNameEnum);
    }
    //通过组名，获取数据源集合对象；
    public static DataSourceGroup getDataSourceGroupByName(DataSourceGroupNameEnum groupNameEnum){
        if( null == groupNameEnum){
            log.warn("DataSourceGroup init getDataSourceGroupByName by groupNameEnum[{}]", groupNameEnum);
            return null;
        }
        DataSourceGroup dataSourceGroup = groupMap.get(groupNameEnum.name());
        return dataSourceGroup;
    }



    //将数据源对象添加到组名管理
    public static void setSlaveDataSourceKey(DataSourceGroupNameEnum groupNameEnum, String... datasourceKey){
        DataSourceGroup dataSourceGroup = groupMap.get(groupNameEnum.name());
        if( null == dataSourceGroup){
            log.warn("DataSourceGroup init setMasterDataSourceKey by dataSourceGroup groupNameEnum[{}]", dataSourceGroup);
            return ;
        }
        if(datasourceKey.length == 1){
            dataSourceGroup.setSlaveSources(datasourceKey[0]);
        }else{
            dataSourceGroup.setSlaveSources(Arrays.asList(datasourceKey));
        }

    }

    public static void setDataSource(DataSourceGroupNameEnum groupNameEnum,String masterDatasourceKey, List<String> slaveDatasources ){
        DataSourceGroup dataSourceGroup = groupMap.get(groupNameEnum.name());
        if( null == dataSourceGroup){
            log.warn("DataSourceGroup init setMasterDataSourceKey by dataSourceGroup groupNameEnum[{}]", dataSourceGroup);
            return ;
        }
        dataSourceGroup.setMasterSources(masterDatasourceKey);
        if(slaveDatasources != null){
            dataSourceGroup.setSlaveSources(slaveDatasources);
        }


    }
    //将数据源对象添加到组名管理
    public static void setDataSourceKey(DataSourceGroupNameEnum groupNameEnum,String masterDatasourceKey, String... slaveDatasourceKey){

        List<String> slaveList = new ArrayList();
        if( slaveDatasourceKey != null){
            slaveList = Arrays.asList(slaveDatasourceKey);
        }
        setDataSource(groupNameEnum,masterDatasourceKey,slaveList);
    }
}
