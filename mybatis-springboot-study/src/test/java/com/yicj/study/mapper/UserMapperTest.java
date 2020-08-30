package com.yicj.study.mapper;

import com.yicj.study.MybatisApp;
import com.yicj.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * ClassName: UserMapperTest
 * Description: TODO(描述)
 * Date: 2020/8/30 17:25
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
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