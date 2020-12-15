package com.yicj.study.conditionalonbean.config;

import com.yicj.study.conditionalonbean.busi.Bean1;
import com.yicj.study.conditionalonbean.busi.Bean2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration1 {

    /**
     * @ConditionalOnBean(Bean2.class)返回false。明明定义的有bean2，bean1却未加载。
     * 首先要明确一点，条件注解的解析一定发生在spring ioc的bean definition阶段，
     * 因为 spring bean初始化的前提条件就是有对应的bean definition，
     * 条件注解正是通过判断bean definition来控制bean能否被解析。
     * bean definition解析的入口开始：ConfigurationClassPostProcessor
     */
    @Bean
    @ConditionalOnBean(Bean2.class)
    public Bean1 bean1() {
        return new Bean1();
    }
}