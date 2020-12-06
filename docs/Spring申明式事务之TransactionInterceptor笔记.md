1. 编写业务方法
    ```java
    @Data
    public class Quote {
        private Integer id ;
        private String name ;
    }
    public interface IQuoteService {
        Quote getQuote() ;
    }
    public class QuoteServiceImpl implements IQuoteService {
        private final JdbcTemplate jdbcTemplate ;
        public QuoteServiceImpl(JdbcTemplate jdbcTemplate){
            this.jdbcTemplate = jdbcTemplate ;
        }
        @Override
        public Quote getQuote() {
            String sql = "select * from quota where id =1" ;
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Quote quota = new Quote() ;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                quota.setId(id);
                quota.setName(name);
                return quota ;
            }) ;
        }
    }
    ```
2. 启动类排除掉TransactionAutoConfiguration类的自动注入（配置类中已手动添加事务相关配置）
    ```java
    @ComponentScan(basePackages = "com.yicj.study.transaction.service")
    @ImportResource({"transaction.xml"})
    @SpringBootApplication(exclude = {TransactionAutoConfiguration.class})
    public class TransactionXmlApplication {
        public static void main(String[] args) {
            SpringApplication.run(TransactionXmlApplication.class, args) ;
        }
    }
    ```
5. 业务对象注入到spring容器中(transaction.xml中代码片段)
    ```xml
    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=GMT" />
        <property name="username" value="root" />
        <property name="password" value="root" />
    </bean>
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" >
        <property name="dataSource" ref="dataSource" />
    </bean>
    <bean id="quoteServiceTarget" class="com.yicj.study.transaction.service.impl.QuoteServiceImpl">
        <constructor-arg ref="jdbcTemplate" />
    </bean>
    ```
5. 使用ProxyFactoryBean + TransactionInterceptor添加声明式事务(transaction.xml中代码片段)
    ```xml
    <bean id="transactionInterceptor" class="org.springframework.transaction.interceptor.TransactionInterceptor">
        <property name="transactionManager" ref="transactionManager" />
        <property name="transactionAttributes">
            <props>
                <prop key="queryById*">PROPAGATION_SUPPORTS,readOnly,timeout_20</prop>
                <prop key="saveQuote">PROPAGATION_REQUIRED</prop>
                <prop key="updateQuote">PROPAGATION_REQUIRED</prop>
                <prop key="deleteQuote">PROPAGATION_REQUIRED</prop>
            </props>
        </property>
    </bean>
    
    <bean id="quoteService" class="org.springframework.aop.framework.ProxyFactoryBean">
        <property name="target" ref="quoteServiceTarget" />
        <property name="proxyInterfaces" value="com.yicj.study.transaction.service.IQuoteService" />
        <property name="interceptorNames">
            <list>
                <value>transactionInterceptor</value>
            </list>
        </property>
    </bean>
    ```
6. 使用一站式的TransactionProxyFactoryBean添加声明式事务(transaction.xml中代码片段)
    ```xml
    <!--TransactionProxyFactoryBean 集成ProxyFactoryBean、TransactionInterceptor功能于一身-->
        <bean id="quoteService" class="org.springframework.transaction.interceptor.TransactionProxyFactoryBean">
            <property name="target" ref="quoteServiceTarget" />
            <property name="transactionManager" ref="transactionManager" />
            <property name="proxyInterfaces" value="com.yicj.study.transaction.service.IQuoteService" />
            <property name="transactionAttributes">
                <props>
                    <prop key="queryById*">PROPAGATION_SUPPORTS,readOnly,timeout_20</prop>
                    <prop key="saveQuote">PROPAGATION_REQUIRED</prop>
                    <prop key="updateQuote">PROPAGATION_REQUIRED</prop>
                    <prop key="deleteQuote">PROPAGATION_REQUIRED</prop>
                </props>
            </property>
        </bean>
    ```
7. 使用BeanNameAutoProxyCreator添加声明式事务(transaction.xml中代码片段)
    ```xml
    <bean id="transactionInterceptor" class="org.springframework.transaction.interceptor.TransactionInterceptor">
        <property name="transactionManager" ref="transactionManager" />
        <property name="transactionAttributes">
            <props>
                <prop key="queryById*">PROPAGATION_SUPPORTS,readOnly,timeout_20</prop>
                <prop key="saveQuote">PROPAGATION_REQUIRED</prop>
                <prop key="updateQuote">PROPAGATION_REQUIRED</prop>
                <prop key="deleteQuote">PROPAGATION_REQUIRED</prop>
            </props>
        </property>
    </bean>
    <bean id="autoProxyCreator" class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
        <property name="interceptorNames">
            <list>
                <value>transactionInterceptor</value>
            </list>
        </property>
        <property name="beanNames">
            <list>
                <idref bean="quoteServiceTarget" />
            </list>
        </property>
    </bean>
    ```
8. 使用spring2.x添加声明式事务(transaction.xml中代码片段)
    ```xml
    <aop:config>
        <aop:pointcut id="txService"
          expression="execution(* com.yicj.study.transaction.service.IQuoteService.*(..))"/>
        <aop:advisor pointcut-ref="txService" advice-ref="txAdvice" />
    </aop:config>
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <tx:method name="queryById*" propagation="SUPPORTS" read-only="true" timeout="20"/>
            <tx:method name="saveQuote" />
            <tx:method name="updateQuote" />
            <tx:method name="deleteQuote" />
        </tx:attributes>
    </tx:advice>
    ```
9. 基于注解驱动的声明式事务,QuoteServiceImpl类需要添加@Transaction标注必要的事务管理信息
    ```xml
    <tx:annotation-driven transaction-manager="transactionManager" />
    ```