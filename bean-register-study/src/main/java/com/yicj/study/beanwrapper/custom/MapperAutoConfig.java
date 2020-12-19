package com.yicj.study.beanwrapper.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MapperAutoConfiguredMyBatisRegistrar.class)
public class MapperAutoConfig {

}
