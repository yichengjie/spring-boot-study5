package com.yicj.study.processor;

import com.yicj.study.component.Flag;
import com.yicj.study.aware.MyAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyAwareProcessor
 * Description: TODO(描述)
 * Date: 2020/8/29 20:27
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Component
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