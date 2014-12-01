package edu.drexel.cs575.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Category resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Category {

	private Integer id;
	private String fullId;
	private String name;
	private RequestError error;
	private Integer uid;
	private String conId;
	
	public Category(){
		
	}

	public Category(Integer id, String fullId, String name, Integer uid, String conId) {
		this.id = id;
		this.name = name;
		this.uid = uid;
		this.fullId = fullId;
		this.conId = conId;
	}

	public Category(RequestError error) {
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}
	
}
