package com.yicj.study.beanwrapper;

import com.yicj.study.beanwrapper.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class MutablePropertyValuesApp {

    public static void main(String[] args) throws ParseException {
        //1. 将属性存入MutablePropertyValues对象中
        MutablePropertyValues pvs = new MutablePropertyValues() ;
        pvs.addPropertyValue(new PropertyValue("id","1")) ;
        pvs.addPropertyValue(new PropertyValue("name","张三")) ;
        pvs.addPropertyValue(new PropertyValue("registerDate","2020-12-19")) ;
        //2. 使用BeanWrapper包装target对象
        Student target = new Student() ;
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(target) ;
        // 日期类属性Editor
        CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true) ;
        wrapper.registerCustomEditor(Date.class, customDateEditor);
        //3. 调用BeanWrapper的Api将MutablePropertyValues设置到target对象中
        wrapper.setPropertyValues(pvs);
        //4. 验证结果
        Assert.assertEquals((Integer) 1, target.getId() );
        Assert.assertEquals("张三", target.getName() );
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-19"), target.getRegisterDate() );
    }
}
