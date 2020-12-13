package com.yicj.study.common.datasource;

import com.yicj.study.model.datasource.DataChooseParam;

public class DataSourceHolder {
    // 数据源名称线程池
    private static final ThreadLocal<DataChooseParam> holder = new ThreadLocal<>();

    public static void putDataSource(DataChooseParam dataChooseParam) {
        holder.set(dataChooseParam);
    }

    public static DataChooseParam getDataSource() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
