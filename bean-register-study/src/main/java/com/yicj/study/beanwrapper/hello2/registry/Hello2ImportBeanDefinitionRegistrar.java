package com.yicj.study.beanwrapper.hello2.registry;

import com.yicj.study.beanwrapper.hello2.EnableHello2ConfigurationProperties;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import java.util.Map;

public class Hello2ImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
//        MultiValueMap<String, Object> attributes = metadata
//                .getAllAnnotationAttributes(EnableHello2ConfigurationProperties.class.getName(), false);
//        List<Object> value = attributes.get("value");
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(EnableHello2ConfigurationProperties.class.getName());
        Class<?> [] values = (Class<?> [])annotationAttributes.get("value");
        for (Class<?> clazz: values){
            registerBeanDefinition(registry, clazz.getName(), clazz) ;
        }
    }

    private void registerBeanDefinition(BeanDefinitionRegistry registry, String name, Class<?> type) {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(type);
        registry.registerBeanDefinition(name, definition);
    }
}
