package com.yicj.study.service.impl;

import com.yicj.study.entity.SysRole;
import com.yicj.study.mapper.SysRoleMapper;
import com.yicj.study.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper sysRoleMapper ;

    @Override
    public void add(SysRole role) {
        sysRoleMapper.insert(role);
    }

    @Override
    public List<SysRole> selectAll() {
        return sysRoleMapper.selectAll();
    }
}
