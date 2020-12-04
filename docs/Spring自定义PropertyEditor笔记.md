1. 编写PropertyEditor实现类
    ```java
    public class DatePropertyEditor extends PropertyEditorSupport {
        private String datePattern;
        public String getDatePattern() {
            return datePattern;
        }
        public void setDatePattern(String datePattern) {
            this.datePattern = datePattern;
        }
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            SimpleDateFormat format = new SimpleDateFormat(getDatePattern());
            try {
                Date dateValue = format.parse(text);
                setValue(dateValue);
                System.out.println("调用了自定义的类型转换器" + dateValue);
            } catch( Exception e) {
                System.out.println("日期格式不对");
            }
        }
        @Override
        public String getAsText() {
            return super.getAsText();
        }
    }
    ```
2. 编写PropertyEditorRegistrar注册PropertyEditor
    ```java
    public class DatePropertyEditorRegistrar implements PropertyEditorRegistrar {
        @Setter
        private PropertyEditor propertyEditor ;
        @Override
        public void registerCustomEditors(PropertyEditorRegistry registry) {
            registry.registerCustomEditor(Date.class, propertyEditor);
        }
    }
    ```
3. 编写业务bean
    ```java
    @Data
    public class DateFoo {
        private Date date;
    }
    ```
3. 编写spring配置文件
    ```xml
    <bean id="datePropertyEditor" class="com.yicj.study.propertyeditor.DatePropertyEditor">
        <property name="datePattern" value="yyyy/MM/dd" />
    </bean>
    <bean id="datePropertyEditorRegistrar" class="com.yicj.study.propertyeditor.DatePropertyEditorRegistrar">
        <property name="propertyEditor"  ref="datePropertyEditor"/>
    </bean>
    <bean class="org.springframework.beans.factory.config.CustomEditorConfigurer">
        <property name="propertyEditorRegistrars">
            <list>
                <ref bean="datePropertyEditorRegistrar" />
            </list>
        </property>
    </bean>
    <bean id="dateFoo" class="com.yicj.study.propertyeditor.DateFoo">
        <property name="date" value="2007/10/16" />
    </bean>
    ```
