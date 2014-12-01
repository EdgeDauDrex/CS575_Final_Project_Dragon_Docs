package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Categories resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Categories {

	private List<Category> categories;
	private RequestError error;
	
	public Categories (){
		
	}

	public Categories(List<Category> categories, RequestError error) {
		this.categories = categories;
		this.error = error;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
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
			for(Category c: categories){
				result += c.toString() + "\n";
			}
			return result;
		}
	}
}
