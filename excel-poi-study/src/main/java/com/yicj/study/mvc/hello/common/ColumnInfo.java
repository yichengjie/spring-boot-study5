package com.yicj.study.mvc.hello.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ColumnInfo {
    private final String fieldName;
    private final String title;
    private final int index;
    private final boolean adaptive;
    private Class<?> columnType ;
}