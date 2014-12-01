package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Users resource - contains either an error or a list of all users
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Users {
	
	private List<User> users = null;
	private RequestError error = null;
	
	public Users(){
		
	}
	
	public Users(List<User> users, RequestError error) {
		this.users = users;
		this.error = error;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}
	
	public int getNumUsers(){
		return users.size();
	}
	
	@Override
	public String toString() {
		String result = "";
		if (error != null){
			return error.toString();
		}else{
			result = "There are " + users.size() + " users!\n";
			for(User u: users){
				result += u.toString() + "\n";
			}
			return result;
		}
	}
	
}
