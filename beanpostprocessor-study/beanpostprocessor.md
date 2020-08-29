#### BeanPostProcessor的使用场景一：通过接口标记，然后批量注入属性
```$xslt
public class MyAwareProcessor implements BeanPostProcessor {
    private final ConfigurableApplicationContext applicationContext ;
    public MyAwareProcessor(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyAware){
            Flag flag = applicationContext.getBean(Flag.class);
            ((MyAware) bean).setFlag(flag);
        }
        return null;
    }
}
```