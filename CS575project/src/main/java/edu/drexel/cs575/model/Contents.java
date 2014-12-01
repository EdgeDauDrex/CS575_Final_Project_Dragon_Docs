package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Contents resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Contents {

	private List<Content> contents;
	private RequestError error;

	public Contents(){
		
	}

	public Contents(List<Content> contents, RequestError error) {
		this.contents = contents;
		this.error = error;
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		String result = "";
		if (error != null){
			return error.toString();
		}else{
			for(Content c: contents){
				result += c.toString() + "\n";
			}
			return result;
		}
	}
	
}
