package com.yicj.study.resultmapper;

import com.yicj.study.common.BaseExportList;
import com.yicj.study.common.ResultMapper;
import com.yicj.study.entity.User;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//https://blog.csdn.net/qq_40233503/article/details/94436578
@Component("userItemResultMapper")
public class UserItemResultMapper implements ResultMapper<User> {

    // 具体返回类型根据Mapper中定义
    @Override
    public Object handler(ResultSet rs) throws SQLException {
        BaseExportList<User> list = new BaseExportList<>() ;
        int row = 1 ;
        while (rs != null && rs.next()){
            list.add(rs.getLong(1) +"", handlerRow(rs, row)) ;
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
