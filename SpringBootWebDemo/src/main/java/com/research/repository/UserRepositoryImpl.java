package com.research.repository;

import com.research.mapper.UserMapper;
import com.research.model.Pagination;
import com.research.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    UserMapper userMapper;


    public User login(User user) {
        return userMapper.login(user);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public List<User> getUsers(Pagination pagination) {
        return userMapper.getUsers(pagination);
    }

    public Integer getUserCounts() {
        return userMapper.getUserCounts();
    }
}
