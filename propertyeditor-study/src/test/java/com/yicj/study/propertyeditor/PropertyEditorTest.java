package com.yicj.study.propertyeditor;

import org.junit.Test;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PropertyEditorTest {


    @Test
    public void test1(){
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("propertyeditor.xml"));

        /*CustomEditorConfigurer是Spring提供的BeanFactoryProcessor的一个实现，专门用来搞类型转换的*/
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();

        DatePropertyEditor my = new DatePropertyEditor();
        my.setDatePattern("yyyy-MM-dd");

        Map customerEditors = new HashMap();
        customerEditors.put(Date.class, DatePropertyEditor.class);

        configurer.setCustomEditors(customerEditors);
        configurer.postProcessBeanFactory(beanFactory);

        DateFoo e = (DateFoo)beanFactory.getBean("entity");
        System.out.println(e.getDate());
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("propertyeditor.xml") ;
        Object dateFoo = applicationContext.getBean("dateFoo");
        System.out.println(dateFoo);
    }
}
