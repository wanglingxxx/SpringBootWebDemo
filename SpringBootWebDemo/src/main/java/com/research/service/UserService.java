package com.research.service;

import com.research.model.Pagination;
import com.research.model.User;
import com.research.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int insertUser(User user) {
        return userRepository.insertUser(user);
    }

    public int deleteUserById(Integer id) {
        return userRepository.deleteUserById(id);
    }

    public int updateUserById(User user) {
        return userRepository.updateUserById(user);
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public List<User> getUsers(Pagination pagination) {
        return userRepository.getUsers(pagination);
    }

    public Integer getUserCounts() {
        return userRepository.getUserCounts();
    }
}
