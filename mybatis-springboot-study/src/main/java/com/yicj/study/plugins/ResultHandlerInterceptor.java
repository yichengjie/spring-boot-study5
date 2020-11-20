package com.yicj.study.plugins;

import com.yicj.study.ApplicationContextUtil;
import com.yicj.study.common.ResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
