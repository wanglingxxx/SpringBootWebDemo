package com.research.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 通知公告
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Notice  implements Serializable{


	private static final long serialVersionUID = -431820144800021547L;
	//主键
	private int id;
	//题名
	private String title;
	//类型(通知或公告)
	private String type;
	//内容
	private String content;
	//发布者
	private String publisher;
	//发布时间
	private Date publishDate;
	//创建时间
	private Date createdDate;
	//修改时间
	private Date lastUpdated;
	
    public Notice() {
		super();
	}


	


	public Notice(int id, String title, String type, String content,
			String publisher, Date publishDate, Date createdDate,
			Date lastUpdated) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.content = content;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.createdDate = createdDate;
		this.lastUpdated = lastUpdated;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
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



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		   return JSONObject.toJSONString(this);
	}

	public JSONObject toJSONObject(){
        return JSONObject.parseObject(JSONObject.toJSONString(this));
    }
}
