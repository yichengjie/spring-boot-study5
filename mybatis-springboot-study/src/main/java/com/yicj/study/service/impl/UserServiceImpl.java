package com.yicj.study.service.impl;

import com.yicj.study.common.MyKeyList;
import com.yicj.study.entity.User;
import com.yicj.study.mapper.UserMapper;
import com.yicj.study.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper ;

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User select4Login(String username, String password) {
        return userMapper.select4Login(username, password);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public MyKeyList<User> selectByUserItemResultMapper() {
        return userMapper.selectByUserItemResultMapper();
    }
}
