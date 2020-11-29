package com.yicj.study.mapper;

import com.yicj.study.MybatisApp;
import com.yicj.study.entity.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApp.class)
public class SysRoleMapperTest {

    @Resource
    private SysRoleMapper sysRoleMapper ;

    @Test
    public void selectAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectAll();
        log.info("infos : {}", sysRoles);
    }

}
