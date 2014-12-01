package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Reviews resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Reviews {

	private List<Review> reviews;
	private RequestError error;
	
	public Reviews(){
		
	}

	public Reviews(List<Review> reviews, RequestError error) {
		this.reviews = reviews;
		this.error = error;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}
	
	public int getNumReviews(){
		return reviews.size();
	}
	
	@Override
	public String toString() {
		String result = "";
		if (error != null){
			return error.toString();
		}else{
			result = "There are " + reviews.size() + " reviews!\n";
			for(Review r: reviews){
				result += r.toString() + "\n";
			}
			return result;
		}
	}
}
