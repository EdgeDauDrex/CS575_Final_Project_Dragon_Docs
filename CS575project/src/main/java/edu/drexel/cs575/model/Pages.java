package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Pages resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Pages {

	private List<Page> pages;
	private RequestError error;
	
	public Pages(){
		
	}

	public Pages(List<Page> pages, RequestError error) {
		this.pages = pages;
		this.error = error;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setFiles(List<Page> pages) {
		this.pages = pages;
	}

	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}
	
	public int getNumPages(){
		return pages.size();
	}
	
	@Override
	public String toString() {
		String result = "";
		if (error != null){
			return error.toString();
		}else{
			result = "There are " + pages.size() + " pages!\n";
			for(Page p: pages){
				result += p.toString() + "\n";
			}
			return result;
		}
	}
}
