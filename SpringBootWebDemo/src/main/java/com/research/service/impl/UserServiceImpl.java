package com.research.service.impl;

import com.research.mapper.UserMapper;
import com.research.model.User;
import com.research.service.UserService;
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
