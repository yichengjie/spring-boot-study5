### 新建starter步骤
a. 新建springboot项目并引入maven依赖
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
```
b. 编写属性源
```$xslt
@Data
@ConfigurationProperties(prefix = "weather")
public class WeatherSource {
    private String type ;
    private String  rate ;
}
```
c. 编写自动配置类
```$xslt
@Configuration
@EnableConfigurationProperties(WeatherSource.class)
@ConditionalOnProperty(name = "weather.enable", havingValue = "enable")
public class WeatherAutoConfiguration {
    @Autowired
    private WeatherSource weatherSource ;

    @Bean
    @ConditionalOnMissingBean(WeatherService.class)
    public WeatherService weatherService(){

        return new WeatherService(weatherSource) ;
    }
}
```
d. 在META-INF/spring.factories中添加自动配置实现类
```$xslt
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.yicj.study.weather.config.WeatherAutoConfiguration
```
e. 通过maven打包安装到仓库


### 使用starter步骤
- a. 在pom.xml中引入starter依赖
- b. 在application.properties中配置相关的属性
- c. 类中引用先关的服务
- d. 调用相关服务

