package edu.drexel.cs575.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Projects resource class
 * 
 * @version 1.0
 */

@JsonInclude(Include.NON_NULL)
public class Projects {

	private List<Project> projects;
	private RequestError error;
	
	public Projects(){
		
	}

	public Projects(List<Project> projects, RequestError error) {
		this.projects = projects;
		this.error = error;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}
	
	public int getNumProjects(){
		return projects.size();
	}
	
	@Override
	public String toString() {
		String result = "";
		if (error != null){
			return error.toString();
		}else{
			result = "There are " + projects.size() + " projects!\n";
			for(Project p: projects){
				result += p.toString() + "\n";
			}
			return result;
		}
	}
}
