package edu.drexel.cs575.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Review resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Review {

	private Integer id;
	private String fullId;
	private Integer uid;
	private String uname;
	private String pid;
	private String review;
	private String reviewDate;
	private RequestError error;
	
	public Review(){
		
	}

	public Review(Integer id, String fullId, Integer uid, String uname, String review, String reviewDate, String pid) {
		this.id = id;
		this.uid = uid;
		this.uname = uname;
		this.review = review;
		this.reviewDate = reviewDate;
		this.fullId = fullId;
		this.pid = pid;
	}

	public Review(RequestError error) {
		this.error = error;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
