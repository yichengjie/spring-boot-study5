package com.yicj.study.mapper;

import com.yicj.study.entity.SysRole;

import java.util.List;

public interface SysRoleMapper {
    void insert(SysRole sysRole) ;

    List<SysRole> selectAll() ;
}
