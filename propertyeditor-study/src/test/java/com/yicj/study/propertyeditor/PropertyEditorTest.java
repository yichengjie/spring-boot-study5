package com.yicj.study.propertyeditor;

import org.junit.Test;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class PropertyEditorTest {

    @Test
    public void test1(){
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("propertyeditor.xml"));
        /*CustomEditorConfigurer是Spring提供的BeanFactoryProcessor的一个实现，专门用来搞类型转换的*/
        //1.  propertyEditor 实例化
        DatePropertyEditor datePropertyEditor = new DatePropertyEditor();
        datePropertyEditor.setDatePattern("yyyy/MM/dd");
        //2. propertyEditorRegistrar实例化
        DatePropertyEditorRegistrar datePropertyEditorRegistrar = new DatePropertyEditorRegistrar() ;
        datePropertyEditorRegistrar.setPropertyEditor(datePropertyEditor);
        //3. customEditorConfigurer 实例化
        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setPropertyEditorRegistrars(new PropertyEditorRegistrar[]{datePropertyEditorRegistrar});
        // 执行beanFactory的后置处理
        configurer.postProcessBeanFactory(beanFactory);
        // 获取实例对象
        DateFoo e = (DateFoo)beanFactory.getBean("dateFoo");
        System.out.println(e.getDate());
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("propertyeditor.xml") ;
        Object dateFoo = applicationContext.getBean("dateFoo");
        System.out.println(dateFoo);
    }
}
