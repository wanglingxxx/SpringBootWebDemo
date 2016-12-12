package com.chen.service.impl;

import com.chen.mapper.UserMapper;
import com.chen.model.User;
import com.chen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by sen on 2016/8/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
