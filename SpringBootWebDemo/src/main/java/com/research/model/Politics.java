package com.research.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * 科研时政表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Politics implements Serializable{


	private static final long serialVersionUID = 8664396454189682824L;
	//主键
	private int id;
	//标题
	private String title;
	//作者
	private String author;
	//来源
	private String origin;
	//内容文件
	private String contentFile;
	//时间
	private Date date;
	//创建时间
	private Date createdDate;
	//修改时间
	private Date lastUpdated;
	
	

	public Politics(int id, String title, String author, String origin,
			String contentFile, Date date, Date createdDate, Date lastUpdated) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.origin = origin;
		this.contentFile = contentFile;
		this.date = date;
		this.createdDate = createdDate;
		this.lastUpdated = lastUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Politics() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getContentFile() {
		return contentFile;
	}

	public void setContentFile(String contentFile) {
		this.contentFile = contentFile;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
