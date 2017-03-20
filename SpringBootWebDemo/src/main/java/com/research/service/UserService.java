package com.research.service;

import com.research.mapper.UserMapper;
import com.research.model.Pagination;
import com.research.model.User;
import com.research.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

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
        if(pagination.getPageIndex() == null) {
            pagination.setPageIndex(0);
        }
        if(pagination.getPageSize() == null) {
            pagination.setPageSize(10);
        }
        pagination.setPageIndex(pagination.getPageIndex() * pagination.getPageSize());
        return userMapper.getUsers(pagination);
    }

    public Integer getUserCounts() {
        return userMapper.getUserCounts();
    }

    public User login(User user) {
        return userMapper.login(user);
    }

    public List<User> queryUsers(String date, String enable) {
        return userMapper.queryUsers(date, enable);
    }
}
