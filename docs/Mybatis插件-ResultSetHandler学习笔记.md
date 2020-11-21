#### 编写基础功能部分
1. 编写Interceptor实现类拦截ResultSetHandler的handleResultSets方法
    ```java
    @Component
    @Intercepts(@Signature(method = "handleResultSets", type = ResultSetHandler.class, args = { Statement.class }))
    public class ResultHandlerInterceptor implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            ResultSetHandler resultSetHandler = (ResultSetHandler)invocation.getTarget() ;
            MetaObject metaObject = SystemMetaObject.forObject(resultSetHandler);
            MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("mappedStatement") ;
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            if (sqlCommandType == SqlCommandType.SELECT){
                String mapper = StringUtils.join(mappedStatement.getResultSets()) ;
                log.info("ResultHandlerInterceptor.intercept,mapperRow:{}", mapper);
                if (!StringUtils.isEmpty(mapper)){
                    Statement statement = (Statement)invocation.getArgs()[0] ;
                    return execHandlerResult(mapper, statement.getResultSet()) ;
                }
            }
            return invocation.proceed();
        }
        private Object execHandlerResult(String mapper, ResultSet rs) throws SQLException {
            try {
                ResultMapper resultMapper = ApplicationContextUtil.getBean4ResultMapper(mapper);
                if (resultMapper != null){
                    return resultMapper.handler(rs) ;
                }
            }finally {
                if (rs != null){
                    rs.close();
                }
            }
            return null ;
        }
    }
    ```
2. 编写结果集映射接口
    ```java
    public interface ResultMapper<T> {
        /**
         * 默认返回List集合，子类可重写此方法返回特定类型
         * eg: Map，或则Integer
         * @param rs
         * @return
         * @throws SQLException
         */
        default Object handler(ResultSet rs) throws SQLException {
            List<T> result = Lists.newArrayList() ;
            int row =1 ;
            while (rs !=null && rs.next()){
                result.add(handlerRow(rs, row)) ;
                row ++ ;
            }
            return result ;
        }
    
        T handlerRow(ResultSet rs, int rowNum) throws SQLException ;
    }
    ```
3. 编写Spring工具获取ResultMapper实现类
    ```java
    @Component
    public class ApplicationContextUtil implements ApplicationContextAware {
        private static ApplicationContext context ;
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context = applicationContext ;
        }
        public static ResultMapper getBean4ResultMapper(String beanName){
            return (ResultMapper)context.getBean(beanName) ;
        }
    }
    ```
#### 编写具体业务部分
1. 编写查询sql, 注意resultSets中的内容userItemResultMapper与后面编写的结果集映射组件id保持一致
    ```xml 
    <select id="selectByUserItemResultMapper" resultSets="userItemResultMapper">
        select id ,username, password, roles from user u
    </select>
    ```
2. 定制查询返回值类型（如果仅返回普通list中封装对象，则不需要写此类）
    ```java
    public class MyKeyList<T> extends ArrayList<T> {
        private Map<String, T> data = new HashMap<>() ;
        public boolean add(String id ,T t) {
            data.put(id, t) ;
            return super.add(t);
        }
        public Map<String,T> getData(){
            return data ;
        }
        public Set<String> getKeys(){
            return data.keySet() ;
        }
    }
    ```
3. 编写Mapper方法
    ```java
    @Mapper
    public interface UserMapper {
        MyKeyList<User> selectByUserItemResultMapper() ;
    }
    ```
4. 编写selectByUserItemResultMapper方法对应结果集映射实现，注意这里bean的id与xml文件中的resultSets内容一致
    ```java
    @Component("userItemResultMapper")
    public class UserItemResultMapper implements ResultMapper<User> {
        // 具体返回类型根据Mapper中定义（如果仅需要返回普通的list则此方法不需要重写）
        @Override
        public Object handler(ResultSet rs) throws SQLException {
            MyKeyList<User> list = new MyKeyList<>() ;
            int row = 1 ;
            while (rs != null && rs.next()){
                list.add(rs.getInt(1) +"", handlerRow(rs, row)) ;
            }
            return list;
        }
        @Override
        public User handlerRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt(1)) ;
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRoles(rs.getString(4));
            return user;
        }
    }
    ```
5. 编写单元测试
    ```java test
    @Test
    public void selectByUserItemResultMapper(){
        MyKeyList<User> list = userMapper.selectByUserItemResultMapper();
        list.forEach(user -> log.info("{}", user));
        Set<String> keys = list.getKeys();
        keys.forEach(id -> log.info("id : {}", id));
    }
    ```

