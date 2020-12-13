package com.yicj.study.model.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装每个项目的数据源对象；
 */
public class DataSourceGroup {
    private String groupName;
    private String masterSources;
    private List<String> slaveSources = new ArrayList<>();
    private String masterMethod;
    private String slaveMethod;


    public String getMasterSources() {
        return masterSources;
    }

    public void setMasterSources(String masterSources) {
        this.masterSources = masterSources;
    }

    public List<String> getSlaveSources() {
        return slaveSources;
    }

    public void setSlaveSources(List<String> slaveSources) {
        this.slaveSources = slaveSources;
    }

    public void setSlaveSources(String slaveKey) {
        if(this.slaveSources == null){
            slaveSources = new ArrayList<>();
        }
        this.slaveSources.add(slaveKey);
    }

    public String getMasterMethod() {
        return masterMethod;
    }

    public void setMasterMethod(String masterMethod) {
        this.masterMethod = masterMethod;
    }

    public String getSlaveMethod() {
        return slaveMethod;
    }

    public void setSlaveMethod(String slaveMethod) {
        this.slaveMethod = slaveMethod;
    }

    public String getGroupName() {
        return groupName;
    }

    public DataSourceGroup setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
}
