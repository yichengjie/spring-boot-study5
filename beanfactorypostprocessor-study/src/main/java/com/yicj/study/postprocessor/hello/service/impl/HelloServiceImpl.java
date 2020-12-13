package com.yicj.study.postprocessor.hello.service.impl;

import com.yicj.study.postprocessor.hello.service.HelloService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    private DataSource dataSource ;

    @Override
    public void hello(String name) throws SQLException {
        System.out.println("=====> " + dataSource.getConnection());
        System.out.println("=====> " + name);
    }
}
