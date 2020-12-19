package com.yicj.study.beanwrapper.hello3;


import com.yicj.study.beanwrapper.hello3.component.OutSide;
import org.springframework.context.annotation.*;

@Import(OutSide.class)
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.yicj.study.beanwrapper.hello3")
public class Entrance {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Entrance.class);
        OutSide outSide = context.getBean("com.yicj.study.importregistrar.hello3.component.OutSide", OutSide.class) ;
        outSide.say();
    }
}
