package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Project resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Project {

	private Integer id;
	private String fullId;
	private String name;
	private String summary;
	private List<Integer> uids;
	private String catid;
	private String creationDate;
	private String updateDate;
	private RequestError error;
	private Boolean reviewable;
	private List<Integer> allowedviewers;
	
	public Project(){
	
	}

	public Project(Integer id, String name, String summary, List<Integer> uids, String catid, String creationDate,
			String updateDate, Boolean reviewable, List<Integer> allowedviewers, String fullId) {
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.uids = uids;
		this.catid = catid;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.reviewable = reviewable;
		this.allowedviewers = allowedviewers;
		this.fullId = fullId;
	}

	public Project(RequestError error) {
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Integer> getUids() {
		return uids;
	}

	public void setUids(List<Integer> uids) {
		this.uids = uids;
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
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

	public Boolean getReviewable() {
		return reviewable;
	}

	public void setReviewable(Boolean reviewable) {
		this.reviewable = reviewable;
	}

	public List<Integer> getAllowedviewers() {
		return allowedviewers;
	}

	public void setAllowedviewers(List<Integer> allowedviewers) {
		this.allowedviewers = allowedviewers;
	}

	public String getFullId() {
		return fullId;
	}

	public void setFullId(String fullId) {
		this.fullId = fullId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allowedviewers == null) ? 0 : allowedviewers.hashCode());
		result = prime * result + ((catid == null) ? 0 : catid.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((fullId == null) ? 0 : fullId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((reviewable == null) ? 0 : reviewable.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((uids == null) ? 0 : uids.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (allowedviewers == null) {
			if (other.allowedviewers != null)
				return false;
		} else if (!allowedviewers.equals(other.allowedviewers))
			return false;
		if (catid == null) {
			if (other.catid != null)
				return false;
		} else if (!catid.equals(other.catid))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (fullId == null) {
			if (other.fullId != null)
				return false;
		} else if (!fullId.equals(other.fullId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reviewable == null) {
			if (other.reviewable != null)
				return false;
		} else if (!reviewable.equals(other.reviewable))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (uids == null) {
			if (other.uids != null)
				return false;
		} else if (!uids.equals(other.uids))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		return true;
	}

}