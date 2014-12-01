package edu.drexel.cs575.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Content resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Content {

	private Integer id;
	private String fullId;
	private String name;
	private RequestError error;
	private Integer uid;
	
	public Content(){
		
	}

	public Content(Integer id, String fullId, String name, Integer uid) {
		this.id = id;
		this.fullId = fullId;
		this.name = name;
		this.uid = uid;
	}

	public Content(RequestError error) {
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

	public Integer getUid() {
		return uid;
	}

	public void setUids(Integer uid) {
		this.uid = uid;
	}

	public String getFullId() {
		return fullId;
	}

	public void setFullId(String fullId) {
		this.fullId = fullId;
	}
	
}
