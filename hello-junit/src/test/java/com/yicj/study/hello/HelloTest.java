package com.yicj.study.hello;

import com.yicj.study.common.JUnit4ClassRunner;
import com.yicj.study.config.MyConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(classes = MyConfig.class)
public class HelloTest{

    @Test
    public void test1(){
        log.info("============");
    }


    @Test
    public void test2(){
        String aa = "AA";//设置常量AA到常量池
        String bb = "BB";//设置常量BB到常量池
        String aabb = aa+bb;//添加AABB对象到堆
        System.out.println();
        System.out.println("==> " + aa.intern() == aa);
        System.out.println("==> " + aabb.intern()==aabb);//true
    }

    @Test
    public void test3(){
        System.getProperties().setProperty() ;
        String a1 = "AA";
        System.out.println(a1 == a1.intern()); //true
        String a2 = new String("B") + new String("B");
        a2.intern();
        String a3 = new String("B") + new String("B");
        System.out.println(a2 == a3.intern());//true
        System.out.println(a3 == a3.intern());//false
        String a4 = new String("C") + new String("C");
        System.out.println(a4 == a4.intern()); //true
    }
}
