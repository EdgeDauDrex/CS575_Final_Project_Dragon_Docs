package edu.drexel.cs575.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Page resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Page {

	private Integer id;
	private String fullId;
	private String name;
	private String projName;
	private String pid;
	private String catid;
	private Integer uid;
	private String summary;
	private String creationDate;
	private String updateDate;
	private RequestError error;
	
	public Page(){
		
	}

	public Page(Integer id, String fullId, String name, String projName, String pid, String catid, Integer uid,
			String summary, String creationDate, String updateDate) {
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.catid = catid;
		this.setUid(uid);
		this.summary = summary;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.fullId = fullId;
		this.projName = projName;
	}

	public Page(RequestError error) {
		super();
		this.error = error;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}

	public String getFullId() {
		return fullId;
	}

	public void setFullId(String fullId) {
		this.fullId = fullId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	
}
