package com.yicj.study.mvc.hello.entity;

import com.yicj.study.mvc.hello.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DogeInfo {
    @ExcelProperty(columnNumber = 4,name = "姓名a",isAdaptive = true)
    private String name;
    @ExcelProperty(columnNumber = 3,name = "年龄a",isAdaptive = true, columnType = Integer.class)
    private String age;
    @ExcelProperty(columnNumber = 1,name = "性别a",isAdaptive = true)
    private String gender;
    @ExcelProperty(columnNumber = 2,name = "注释a",isExport = false)
    private String mark;
}