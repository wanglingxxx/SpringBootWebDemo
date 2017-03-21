package com.research.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination implements Serializable{


	private static final long serialVersionUID = 1871202257627262259L;
	private Integer total;
	
	private Integer pageIndex;
	
	private Integer pageSize;

	private Integer userId;

	private String date;

	private String state;

	public Pagination(Integer total, Integer pageIndex, Integer pageSize, Integer userId, String date, String state) {
		this.total = total;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.userId = userId;
		this.date = date;
		this.state = state;
	}

	public Pagination() {
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public JSONObject toJSONObject(){
		return JSONObject.parseObject(JSONObject.toJSONString(this));
	}
	
}
