package com.research.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 科研成果
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Achievement implements Serializable{

	private static final long serialVersionUID = -2812955151724211938L;
	//主键
	private int id;
	//项目名
	private String projectName;
	//项目人
	private String projectMan;
	//获奖情况
	private String awardSituation;
	//内容文件
	private String contentFile;
	//发布时间
	private Date publishDate;
	//创建时间
	private Date createdDate;
	//修改时间
	private Date lastUpdated;

	

	public Achievement(int id, String projectName, String projectMan,
			String awardSituation, String contentFile, Date publishDate,
			Date createdDate, Date lastUpdated) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.projectMan = projectMan;
		this.awardSituation = awardSituation;
		this.contentFile = contentFile;
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

	public Achievement() {
		super();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectMan() {
		return projectMan;
	}

	public void setProjectMan(String projectMan) {
		this.projectMan = projectMan;
	}

	public String getAwardSituation() {
		return awardSituation;
	}

	public void setAwardSituation(String awardSituation) {
		this.awardSituation = awardSituation;
	}

	public String getContentFile() {
		return contentFile;
	}

	public void setContentFile(String contentFile) {
		this.contentFile = contentFile;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
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
