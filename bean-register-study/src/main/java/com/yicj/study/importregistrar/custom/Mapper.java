package com.yicj.study.importregistrar.custom;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,ElementType.PARAMETER})
public @interface Mapper {

}
