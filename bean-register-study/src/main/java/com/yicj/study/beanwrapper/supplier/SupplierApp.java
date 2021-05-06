package com.yicj.study.beanwrapper.supplier;

import com.yicj.study.beanwrapper.factorybean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Date;
import java.util.function.Supplier;

@Slf4j
public class SupplierApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
        context.registerBean(User.class,new Supplier<User>(){
            @Override
            public User get() {
                User user = new User();
                user.setName(new Date().toString());
                return user;
            }
        } ,new BeanDefinitionCustomizer(){
            @Override
            public void customize(BeanDefinition bd) {
                bd.setPrimary(true);
                log.info("===> bd : {}", bd);
            }
        });
        User user = (User) context.getBean("user");
        log.info("===> user :{}",user.getName());
    }
}
