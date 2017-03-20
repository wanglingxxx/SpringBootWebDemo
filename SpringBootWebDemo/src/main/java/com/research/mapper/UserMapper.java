package com.research.mapper;

import com.research.model.Pagination;
import com.research.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {


    User login(User user);

    int insertUser(User user);

    int deleteUserById(Integer id);

    int updateUserById(User user);

    User getUserById(Integer id);

    List<User> getUsers(Pagination pagination);

    Integer getUserCounts();

    List<User> queryUsers(@Param("date")String date, @Param("enable") String enable);
}
