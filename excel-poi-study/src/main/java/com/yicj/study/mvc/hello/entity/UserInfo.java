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
public class UserInfo {
    @ExcelProperty(columnNumber = 1,name = "姓名",isAdaptive = true)
    private String name;
    @ExcelProperty(columnNumber = 2,name = "年龄",isAdaptive = true)
    private String age;
    @ExcelProperty(columnNumber = 3,name = "性别",isAdaptive = true)
    private String gender;
    @ExcelProperty(columnNumber = 4,name = "注释",isExport = false)
    private String mark;
}