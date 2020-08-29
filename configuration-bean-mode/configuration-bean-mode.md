### 注解方式配置bean的方式
##### @Component声明
##### 配置类中使用@Bean
##### 实现FactoryBean
```$xslt
@Component
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Monky();
    }
    @Override
    public Class<?> getObjectType() {
        return Monky.class;
    }
}
```
##### 实现BeanDefinitionRegistryPostProcessor
```$xslt
@Component
public class MyBeanRegister implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition() ;
        rootBeanDefinition.setBeanClass(Cat.class);
        registry.registerBeanDefinition("cat", rootBeanDefinition);
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {}
}
```
##### 通过实现ImportBeanDefinitionRegistrar接口注册
>> 第一步：编写注册类
```$xslt
public class MyBeanImport implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition() ;
        rootBeanDefinition.setBeanClass(Dog.class);
        registry.registerBeanDefinition("dog", rootBeanDefinition);
    }
}
```
>> 第二步：在启动类上添加@Import注解导入注册类
```$xslt
@Import(MyBeanImport.class)
@SpringBootApplication
public class ConfModeApp {
    public static void main(String[] args) {
        SpringApplication.run(ConfModeApp.class, args) ;
    }
}
```

