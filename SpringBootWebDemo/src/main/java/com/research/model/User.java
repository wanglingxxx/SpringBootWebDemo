package com.research.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{

    private static final long serialVersionUID = 6902702777401204196L;
    //主键
    private int id;
    //用户名
    private String username;
    //密码
    private String password;
    //姓名
    private String name;
    //性别
    private String sex;
    //身份证号
    private String idCard;
    //权限
    private Integer enable;
    //创建时间
    private Date createdDate;
    //修改时间
    private Date lastUpdated;


    public User() {
        super();
    }



    public User(int id, String username, String password, String name,
                String sex, String idCard, Integer enable, Date createdDate,
                Date lastUpdated) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.idCard = idCard;
        this.enable = enable;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public JSONObject toJSONObject(){
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
