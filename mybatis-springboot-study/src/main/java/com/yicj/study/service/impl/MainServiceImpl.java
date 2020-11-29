package com.yicj.study.service.impl;

import com.yicj.study.entity.SysRole;
import com.yicj.study.entity.User;
import com.yicj.study.service.MainService;
import com.yicj.study.service.SysRoleService;
import com.yicj.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MainServiceImpl  implements MainService {
    @Autowired
    private UserService userService ;
    @Autowired
    private SysRoleService sysRoleService ;

    @Override
    public void save(User user, SysRole sysRole) {
        userService.insert(user) ;
        int a = 1/0 ;
        sysRoleService.add(sysRole);
    }
}
