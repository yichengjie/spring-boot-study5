package com.yicj.study.importregistrar.hello2;

import com.yicj.study.importregistrar.hello2.registry.Hello2ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 使用ImportSelector注册ImportBeanDefinitionRegistrar
// 直接注册ImportBeanDefinitionRegistrar
//@Import(EnableHello2ConfigurationPropertiesImportSelector.class)
@Import(Hello2ImportBeanDefinitionRegistrar.class)
public @interface EnableHello2ConfigurationProperties {
    Class<?>[] value() default {};
}
