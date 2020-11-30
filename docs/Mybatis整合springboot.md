1. 添加依赖
    ```xml
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.0</version>
    </dependency>
    ```
2. 编写mybatis主配文件(mybatis-config.xml)
    ```xml
    <configuration>
        <!--类型别名配置-->
        <typeAliases>
           <package name="com.yicj.study.entity"/>
        </typeAliases>
        <!--配置MyBatis 去哪里找映射文件-->
        <!--注意如果这里使用package的话路径一定要与java的mapper接口包名称一致，否则将找不到xml文件-->
        <mappers>
            <package name="com.yicj.study.mapper"/>
        </mappers>
    </configuration>
    ```
3. 修改application.properties配置文件
    ```properties
    # mybatis 主配置文件地址
    mybatis.config-location=classpath:mybatis-config.xml
    # 检查配置文件是否存在的开关(如果确实没有配置mybatis的配置文件,这个就可以不用配置了)
    mybatis.check-config-location=true
    ```
4. 添加注解@MapperScan为接口生成代理类
    ```java
    @MapperScan("com.yicj.study.mapper")
    ```
5. 单元测试
    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = MybatisApp.class)
    public class UserMapperTest {
        @Resource
        private UserMapper userMapper ;
        @Test
        public void selectById(){
            Integer id = 1 ;
            User user = userMapper.selectById(id);
            log.info("user info : {}", user);
        }
    }
    ```

