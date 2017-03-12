package com.research.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination implements Serializable{

	private static final long serialVersionUID = 8328302837899004998L;
	
	private Integer total;
	
	private Integer pageIndex;
	
	private Integer pageSize;

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

	public Pagination() {
		super();
	}

	public Pagination(Integer total, Integer pageIndex, Integer pageSize) {
		super();
		this.total = total;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public JSONObject toJSONObject(){
		return JSONObject.parseObject(JSONObject.toJSONString(this));
	}
	
}
