package com.yicj.study;

import com.yicj.study.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config-test.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        String  statementId = "com.yicj.study.mapper.UserMapper.selectById" ;
        //String  statementId = "selectById" ;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            User user = session.selectOne(statementId, 1);
            System.out.println(user);
        }
    }
}
