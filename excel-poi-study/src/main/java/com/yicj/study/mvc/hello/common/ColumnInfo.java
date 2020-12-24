package com.yicj.study.mvc.hello.common;

import lombok.Data;

@Data
public class ColumnInfo {
    private String fieldName;
    private String title;
    private int index;
    private boolean adaptive;

    public ColumnInfo(String fieldName, String title){
       this(fieldName, title, 0 , true) ;
    }

    public ColumnInfo(String fieldName, String title, int index, boolean adaptive){
        this.fieldName = fieldName ;
        this.title = title ;
        this.index = index ;
        this.adaptive = adaptive ;
    }
    private Class<?> columnType ;
}