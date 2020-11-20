package com.yicj.study.common;

import org.assertj.core.util.Lists;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultMapper {

    default Object handler(ResultSet rs) throws SQLException {
        List result = Lists.newArrayList() ;
        int row =1 ;
        while (rs !=null && rs.next()){
            result.add(handlerRow(rs, row)) ;
            row ++ ;
        }
        return result ;
    }

    Object handlerRow(ResultSet rs, int rowNum) throws SQLException ;
}
