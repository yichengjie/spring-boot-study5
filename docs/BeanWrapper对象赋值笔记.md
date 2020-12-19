1. 编写业务实体类
    ```java
    @Data
    public class Student {
        private Integer id ;
        private String name ;
        private Date registerDate ;
    }
    ```
2. 将属性值放入MutablePropertyValues对象
    ```text
    // 将属性存入MutablePropertyValues对象中
    MutablePropertyValues pvs = new MutablePropertyValues() ;
    pvs.addPropertyValue(new PropertyValue("id","1")) ;
    pvs.addPropertyValue(new PropertyValue("name","张三")) ;
    pvs.addPropertyValue(new PropertyValue("registerDate","2020-12-19")) ;
    ```
3. 使用BeanWrapper包装target对象
    ```text
    Student target = new Student() ;
    BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(target) ;
    // 给BeanWrapper注册自定义PropertyEditor
    CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true) ;
    wrapper.registerCustomEditor(Date.class, customDateEditor);
    ```
4. 调用BeanWrapper的Api将MutablePropertyValues设置到target对象中
    ```text
    wrapper.setPropertyValues(pvs);
    ```
5. 验证结果
    ```text
    Assert.assertEquals((Integer) 1, target.getId() );
    Assert.assertEquals("张三", target.getName() );
    Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-19"), target.getRegisterDate() );
    ```