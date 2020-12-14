package com.yicj.study.common.datasource;

public class DataSourceTypeManager {
    private static ThreadLocal<DataSourceType> threadLocal =
            ThreadLocal.withInitial(() -> DataSourceType.MAIN) ;
            
    public static DataSourceType get(){
        return threadLocal.get() ;
    }
    public static void set(DataSourceType type){
        threadLocal.set(type);
    }
}