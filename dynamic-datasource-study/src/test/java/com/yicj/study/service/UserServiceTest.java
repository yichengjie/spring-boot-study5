package com.yicj.study.service;


import com.yicj.study.DynamicDatasourceApp;
import com.yicj.study.common.datasource.DataSourceHolder;
import com.yicj.study.model.datasource.DataChooseParam;
import com.yicj.study.model.datasource.DataSourceEnum;
import com.yicj.study.model.datasource.DataSourceGroupNameEnum;
import com.yicj.study.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicDatasourceApp.class)
public class UserServiceTest {

    @Autowired
    private UserService userService ;

    @Test
    public void save(){
        User user = User.builder()
                .username("李四")
                .password("123")
                .roles("test")
                .build() ;
        userService.insert(user) ;
    }

    @Test
    public void selectAll(){
        DataSourceGroupNameEnum dataNameAssets = DataSourceGroupNameEnum.DATA_NAME_ASSETS;
        DataChooseParam dataChooseParam = new DataChooseParam(dataNameAssets.name(), DataSourceEnum.MASTER) ;
        DataSourceHolder.putDataSource(dataChooseParam);
        List<User> users = userService.selectAll();
        log.info("====> {}", users);
    }

    @Test
    public void select4Login(){
        String username = "yicj" ;
        String password = "123" ;
        User user = userService.select4Login(username, password);
        log.info("user : {}", user);
    }

}
