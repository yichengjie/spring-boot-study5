package com.yicj.study.service.impl;

import com.yicj.study.common.MyKeyList;
import com.yicj.study.entity.User;
import com.yicj.study.mapper.UserMapper;
import com.yicj.study.service.UserService;
import com.yicj.study.vo.LoginParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<User> select4Login(LoginParam loginParam) {
        return userMapper.select4Login(loginParam);
    }

    @Override
    public int insert(User user) {
        userMapper.insert(user);
        int a = 1/0 ;
        return 1 ;
    }

    @Override
    public MyKeyList<User> selectByUserItemResultMapper() {
        return userMapper.selectByUserItemResultMapper();
    }
}
