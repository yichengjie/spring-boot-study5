package com.yicj.study.service.impl;

import com.yicj.study.mapper.UserMapper;
import com.yicj.study.model.entity.User;
import com.yicj.study.service.UserService;
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
    public List<User> selectAll() {
        List<User> users = userMapper.selectAll();
        return users ;
    }

    @Override
    public User select4Login(String username, String password) {
        return userMapper.select4Login(username, password);
    }

    @Transactional(timeout = 5)
    @Override
    public int insert(User user) {
        userMapper.insert(user);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1 ;
    }
}
