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
public class DogeInfo {
    @ExcelAttribute(column = 4,name = "姓名a",isAdaptive = true)
    private String name;
    @ExcelAttribute(column = 3,name = "年龄a",isAdaptive = true)
    private String age;
    @ExcelAttribute(column = 1,name = "性别a",isAdaptive = true)
    private String gender;
    @ExcelAttribute(column = 2,name = "注释a",isExport = false)
    private String mark;
}