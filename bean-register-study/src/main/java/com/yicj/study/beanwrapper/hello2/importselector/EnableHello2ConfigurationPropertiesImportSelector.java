package com.yicj.study.beanwrapper.hello2.importselector;

import com.yicj.study.beanwrapper.hello2.registry.Hello2ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class EnableHello2ConfigurationPropertiesImportSelector  implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                Hello2ImportBeanDefinitionRegistrar.class.getName()
        };
    }
}
