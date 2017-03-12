package com.research.repository;

import com.research.model.Pagination;
import com.research.model.User;

import java.util.List;

public interface UserRepository {


    int insertUser(User user);

    int deleteUserById(Integer id);

    int updateUserById(User user);

    User getUserById(Integer id);

    List<User> getUsers(Pagination pagination);

    Integer getUserCounts();
}
