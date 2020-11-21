package com.yicj.study.common;

import org.assertj.core.util.Lists;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
