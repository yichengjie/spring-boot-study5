package com.yicj.study.mvc.hello.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelProperty {
    //列名
    String name();
    //列序号
    int columnNumber();
    // 单元格数据类型（日期，数字，字符串等）
    Class<?> columnType() default String.class;
    //是否需要导出
    boolean isExport() default true;
    //单元格是否需要自适应
    boolean isAdaptive() default false;
}