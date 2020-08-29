### 系统初始化器的编写
实现implements ApplicationContextInitializer<ConfigurableApplicationContext>接口即可
```$xslt
public class FirstInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Map<String,Object> map = new HashMap<>() ;
        map.put("key1", "value1") ;
        MapPropertySource mapPropertySource = new MapPropertySource("firstInitializer", map);
        environment.getPropertySources().addLast(mapPropertySource);
        log.info("======> run firstInitializer");
    }
}
```


### 系统初始化器的注册
#### 方式1：硬编码方式(手动添加注册)
```
SpringApplication application = new SpringApplication(BasicApiApp.class);
application.addInitializers(new FirstInitializer());
application.run(args) ;
```

#### 方式2：META-INF/spring.factories文件中添加（SpringFactoriesLoader发现注册）
```
org.springframework.context.ApplicationContextInitializer=com.yicj.study.initializers.SecondInitializer
```

#### 方式3：在application.properties中添加 (DelegatingApplicationContextInitializer发现注册)
```$xslt
context.initializer.classes=com.yicj.study.initializers.ThirdInitializer
```