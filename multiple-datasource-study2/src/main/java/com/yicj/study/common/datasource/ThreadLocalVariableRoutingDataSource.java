package com.yicj.study.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ThreadLocalVariableRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DataSourceTypeManager.get();
        return dataSourceType ;
    }
}