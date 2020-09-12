package com.yicj.study.importregistrar.custom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MapperAutoConfiguredMyBatisRegistrar.class)
public class MapperAutoConfig {

}
