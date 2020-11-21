1. 编写Interceptor实现类拦截ResultSetHandler的handleResultSets方法
    ```java
    @Slf4j
    @Intercepts(@Signature(method = "handleResultSets", type = ResultSetHandler.class, args = { java.sql.Statement.class }))
    public class ResultHandlerInterceptor implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            ResultSetHandler resultSetHandler = (ResultSetHandler)invocation.getTarget() ;
            MappedStatement mappedStatement = null ; /*(MappedStatement) ReflectionUtils.getFieldValue(resultSetHandler,
                    "mappedStatement");*/
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
                ResultMapper resultMapper = ApplicationContextUtil.getBeanIgnoreEx(mapper);
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
