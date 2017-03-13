package com.research.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * 项目表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Serializable{


	private static final long serialVersionUID = -5088847674368360596L;
	//主键
    private int id;
    //标题
    private String title;
    //简介
    private String summary;
    //申报者
    private String applicant;
    //申报单位
    private String declareUnits;
    //备注
    private String remark;
    //申报时间
    private Date declareTime;
    //审核通过时间
    private Date auditPassTime;
    //审核状态
    private String auditState;
    //项目文件
    private String projectFile;
    //创建时间
    private Date createdDate;
    //修改时间
    private Date lastUpdated;
	
	
	public Project(int id, String title, String summary, String applicant,
			String declareUnits, String remark, Date declareTime,
			Date auditPassTime, String auditState, String projectFile,
			Date createdDate, Date lastUpdated) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.applicant = applicant;
		this.declareUnits = declareUnits;
		this.remark = remark;
		this.declareTime = declareTime;
		this.auditPassTime = auditPassTime;
		this.auditState = auditState;
		this.projectFile = projectFile;
		this.createdDate = createdDate;
		this.lastUpdated = lastUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getDeclareUnits() {
		return declareUnits;
	}

	public void setDeclareUnits(String declareUnits) {
		this.declareUnits = declareUnits;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDeclareTime() {
		return declareTime;
	}

	public void setDeclareTime(Date declareTime) {
		this.declareTime = declareTime;
	}

	public Date getAuditPassTime() {
		return auditPassTime;
	}

	public void setAuditPassTime(Date auditPassTime) {
		this.auditPassTime = auditPassTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(String projectFile) {
		this.projectFile = projectFile;
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
