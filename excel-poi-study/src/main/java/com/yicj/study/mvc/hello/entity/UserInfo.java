package com.yicj.study.mvc.hello.entity;

import com.yicj.study.mvc.hello.annotation.ExcelAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @ExcelAttribute(column = 1,name = "姓名",isAdaptive = true)
    private String name;
    @ExcelAttribute(column = 2,name = "年龄",isAdaptive = true)
    private String age;
    @ExcelAttribute(column = 3,name = "性别",isAdaptive = true)
    private String gender;
    @ExcelAttribute(column = 4,name = "注释",isExport = false)
    private String mark;
}