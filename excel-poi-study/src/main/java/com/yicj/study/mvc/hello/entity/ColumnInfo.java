package com.yicj.study.mvc.hello.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColumnInfo {
    private String fieldName;
    private String title;
    private int index;
    private boolean adaptive;
}