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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertUser(@RequestBody User user) {

        Preconditions.checkArgument(user != null, "User is null");

        user.setCreatedDate(new Date());
        user.setLastUpdated(new Date());
        user.setEnable(0);
        logger.info("/user/ insertUser : {}",user);

        int count = userService.insertUser(user);

        if(count == 0) {
            logger.warn("/user/ insertUser return null ");
            return ResponseEntity.noContent().build();
        } else {
            user.setId(count);
            logger.info("/user/ insertUser return :{}",user);
            return ResponseEntity.ok(user);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteUserById(Integer id) {
        Preconditions.checkArgument(id != 0, "User id is illegal");

        int count = userService.deleteUserById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/usr/{id}/ deleteUserById return :{}", id);
            return ResponseEntity.ok(id);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateUserById(@RequestParam(value="id") Integer id,@RequestParam(value="username",required = false)String username,
                                     @RequestParam(value="name",required = false)String name, @RequestParam(value="sex",required = false)String sex,
                                     @RequestParam(value="idCard",required = false)String idCard,@RequestParam(value="enable",required = false) Integer enable,
                                     @RequestParam(value="password",required = false)String password) {
        Preconditions.checkArgument(id != null && id > 0, "User id is illegal");

        User user = new User();
        user.setId(id);
        if(username != null && username.length() != 0){
            user.setUsername(username);
        }
        if(name != null && name.length() != 0){
            user.setName(name);
        }
        if(sex != null && sex.length() != 0){
            user.setSex(sex);
        }

        if(idCard != null && idCard.length() != 0){
            user.setIdCard(idCard);
        }
        if(enable != null && enable >= 0){
            user.setEnable(enable);
        }
        if(password != null && password.length() != 0){
            user.setPassword(password);
        }

        user.setLastUpdated(new Date());

        int count = userService.updateUserById(user);

        if(count == 0) {
            logger.info("/user/update updateUserById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/user/update updateUserById return :{}",user);
            return ResponseEntity.ok(user);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        Preconditions.checkArgument(id != 0, "User id is illegal");


        User user = userService.getUserById(id);
        logger.info("/usr/{id}/ getUserById return :{}"+ user);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Preconditions.checkArgument(username != null && username.length() > 0, "User username is illegal");
        Preconditions.checkArgument(password != null && password.length() > 0, "User password is illegal");

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
    ResponseEntity<?> getUsers(@RequestParam(value = "conditions", defaultValue = "all") String conditions) {
        //TODO 完成条件查询
        List<User> users;
        Pagination pagination = new Pagination();
        if(conditions.equals("all")) {
            pagination.setPageSize(100);
            users = userService.getUsers(pagination);
        } else if(conditions.equals("recent")) {
            User project = new User();
            Date date = new Date();
            date.setMonth(date.getMonth()-1);
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
            String condition = formatter.format(date);
            users = userService.queryUsers(condition ,"");
        } else if(conditions.equals("1")) {
            users = userService.queryUsers("", "1");
        } else if(conditions.equals("0")) {
            users = userService.queryUsers("", "0");
        } else {
            logger.info("/user/query return :{}","");
            return ResponseEntity.noContent().build();
        }

        logger.info("/user/query return :{}",users);
        return ResponseEntity.ok(users);
    }
}
