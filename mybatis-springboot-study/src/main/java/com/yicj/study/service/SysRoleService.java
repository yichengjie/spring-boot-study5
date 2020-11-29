package com.yicj.study.service;

import com.yicj.study.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    void add(SysRole role) ;

    List<SysRole> selectAll() ;
}
