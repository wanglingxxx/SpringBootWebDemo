package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Pagination;
import com.research.model.User;
import com.research.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertUser(@RequestBody User user) {

        Preconditions.checkNotNull(user != null, "User is null");

        int count = userService.insertUser(user);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteUserById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "User id is illegal");

        int count = userService.deleteUserById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateUserById(@RequestBody User user) {
        Preconditions.checkNotNull(user != null && user.getId() != 0, "User id is illegal");

        int count = userService.updateUserById(user);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "User id is illegal");


        User user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/login/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Preconditions.checkNotNull(username != null && username.length() > 0, "User username is illegal");
        Preconditions.checkNotNull(password != null && password.length() > 0, "User password is illegal");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        logger.info("/user/login/ receive params :{}" + user.toJSONObject());


        user = userService.login(user);
        if(user != null) {
            logger.info("/usr/login return :{}"+ user.toJSONObject());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getUsers(Pagination pagination) {
        Preconditions.checkNotNull(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkNotNull(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<User> users = userService.getUsers(pagination);

        return ResponseEntity.ok(users);
    }
}
