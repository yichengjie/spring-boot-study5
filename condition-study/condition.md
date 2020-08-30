#### 常用的condition注解
```$xslt
@ConditionalOnBean                   @ConditionalOnMissingBean
@ConditionalOnClass                  @ConditionalOnMissingClass
@ConditionalOnProperty               @ConditionalOnJava
@ConditionalOnWebApplication         @ConditionalOnNotWebApplication
```

#### 自定义Condition条件注解
a. 编写Condition实现类，并实现matches方法
```$xslt
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String annotationName ="com.yicj.study.custom.MyConditionAnno" ;
        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(annotationName);
        String [] props = (String[])attributes.get("value");
        for (String prop: props){
            String val = context.getEnvironment().getProperty(prop);
            if (StringUtils.isEmpty(val)){
                return false;
            }
        }
        return true;
    }
}
```
b. 编写Condition注解类
```$xslt
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(MyCondition.class)
public @interface MyConditionAnno {

    String [] value() default {} ;
}
```
c. 在application.properties中添加Condition生效条件
```$xslt
com.yicj.condition2=test2
com.yicj.condition3=test3
```
d. 在业务bean上添加自定义Condition注解(注意Component不能少)
```$xslt
@Component
@MyConditionAnno({"com.yicj.condition2","com.yicj.condition3"})
public class B {
}
```
