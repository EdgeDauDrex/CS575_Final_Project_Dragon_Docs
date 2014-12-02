package edu.drexel.cs575.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.drexel.cs575.data.DB;
import edu.drexel.cs575.model.Categories;
import edu.drexel.cs575.model.Category;
import edu.drexel.cs575.model.Content;
import edu.drexel.cs575.model.Contents;
import edu.drexel.cs575.model.Page;
import edu.drexel.cs575.model.Pages;
import edu.drexel.cs575.model.Project;
import edu.drexel.cs575.model.Projects;
import edu.drexel.cs575.model.RequestError;
import edu.drexel.cs575.model.Review;
import edu.drexel.cs575.model.Reviews;
import edu.drexel.cs575.model.User;
import edu.drexel.cs575.model.Users;

/**
 * This is the controller for Dragon Docs.
 * It has a lot of functions to resolve a lot of views.
 * 
 * @version 1.0
 *
 */
@Controller
public class DocumentController {

	@Autowired
	DB dbService;
	
	@RequestMapping("/start")
	public ModelAndView index(@RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		ModelAndView model = new ModelAndView("home");
		model.addObject("name", user.getUsername());
		model.addObject("auth",auth);
		return model;
	}
	
	@RequestMapping("/new_user")
	public ModelAndView newUser(){
		ModelAndView model = new ModelAndView("signup");
		return model;
	}
	
	@RequestMapping("/user_success")
	public ModelAndView userMade(@RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		ModelAndView model = new ModelAndView("user_made");
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping("/user_fail")
	public ModelAndView userFailed(){
		ModelAndView model = new ModelAndView("failed_user");
		return model;
	}
	
	@RequestMapping(value="/users")
	public ModelAndView getAllUsers(@RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		List<User> uList = dbService.getUsers();
		RequestError error = null;
		if (uList == null){
			error = new RequestError(404, "Unable to read users.json");
		}
		Users users = new Users(uList,error);
		ModelAndView model = new ModelAndView("all_users");
		model.addObject("users", users);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}")
	public ModelAndView getUser(@PathVariable String name, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		User target;
		try{
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){
			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error);
		}
		ModelAndView model = new ModelAndView("single_user");
		model.addObject("user", target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/bio")
	public ModelAndView getBio(@PathVariable String name, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		User target;
		try{
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){
			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error);
		}
		ModelAndView model = new ModelAndView("bio_edit");
		model.addObject("user", target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/projects")
	public ModelAndView getAllContents(@RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		List<Project> pList = dbService.getProjects();
		RequestError error = null;
		if (pList == null){
			error = new RequestError(404, "Unable to read projects.json");
		}
		Projects contents = new Projects(pList,error);
		ModelAndView model = new ModelAndView("all_content_list");
		model.addObject("contents",contents);
		//model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents")
	public ModelAndView getUsersContents(@PathVariable String name, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		List<Content> cList;
		User target;
		try{
			cList = dbService.getContentsByUser(Integer.parseInt(name));
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){
			cList = dbService.getContentsByUsername(name);
			target = dbService.getUserByName(name);
		}
		RequestError error = null;
		if(cList == null){
			error = new RequestError(404, "Unable to read the contents.");
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		Contents contents = new Contents(cList,error);
		ModelAndView model = new ModelAndView("content_list");
		model.addObject("contents",contents);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}")
	public ModelAndView getSingleContents(@PathVariable String name, @PathVariable String con, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		ModelAndView model = new ModelAndView("single_contents");
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories")
	public ModelAndView getCategories(@PathVariable String name, @PathVariable String con, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		List<Category> cList;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
			cList = dbService.getCategoriesByContent("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
				cList = dbService.getCategoriesByContentname(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
				cList = dbService.getCategoriesByContentname(target.getUsername() + "'s " + con);
			}
		}
		RequestError error3 = null;
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		if(cList == null){
			error3 = new RequestError(404, "Unable to read categories.json");
		}
		Categories categories = new Categories(cList,error3);
		ModelAndView model = new ModelAndView("category_list");
		model.addObject("categories",categories);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}")
	public ModelAndView getCategory(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		ModelAndView model = new ModelAndView("single_category");
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/new_project")
	public ModelAndView newProject(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		ModelAndView model = new ModelAndView("create_project");
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects")
	public ModelAndView getProjects(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		List<Project> pList = dbService.getProjectsByCategory(category.getFullId());
		RequestError error4 = null;
		if(pList == null){
			error4 = new RequestError(404, "Unable to read projects.json");
		}
		Projects projects = new Projects(pList,error4);
		ModelAndView model = new ModelAndView("project_list");
		model.addObject("projects", projects);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/project_fail")
	public ModelAndView getProjectFail(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		List<Project> pList = dbService.getProjectsByCategory(category.getFullId());
		RequestError error4 = null;
		if(pList == null){
			error4 = new RequestError(404, "Unable to read projects.json");
		}
		Projects projects = new Projects(pList,error4);
		ModelAndView model = new ModelAndView("failed_project");
		model.addObject("projects", projects);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}")
	public ModelAndView getProject(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		ModelAndView model;
		if(project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("single_project");
		}
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages")
	public ModelAndView getPages(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		List<Page> pList = dbService.getPagesByProject(project.getFullId());
		RequestError error5 = null;
		if(pList == null){
			error5 = new RequestError(404, "Unable to read pages.json");
		}
		Pages pages = new Pages(pList,error5);
		ModelAndView model;
		if(project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("page_list");
		}
		model.addObject("pages", pages);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/new_page")
	public ModelAndView newPage(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		ModelAndView model;
		if(project.getUids() != null && !project.getUids().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("create_page");
		}
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/project_edit")
	public ModelAndView editPage(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		ModelAndView model;
		if(project.getUids() != null && !project.getUids().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("update_project");
		}
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages/{pnum}")
	public ModelAndView getPage(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @PathVariable String pnum, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		Page page = null;
		try{
			page = dbService.getPageByID(project.getFullId() + "PAGE" + Integer.parseInt(pnum));
		}catch(Exception e){
			List<Page> pageList = dbService.getPagesByProject(project.getFullId());
			for(Page p : pageList){
				if(p.getName() == pnum){
					page = p;
				}
			}
		}
		if(page == null){
			RequestError error5 = new RequestError(404, "Unable to read page");
			page = new Page(error5);
		}
		ModelAndView model;
		if(project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("single_page");
		}
		String filePath = dbService.getPRIMARY_STORAGE() + target.getUsername() + "\\" + content.getFullId() + "\\" + category.getFullId() + "\\" + project.getFullId() + "\\" + page.getFullId() + ".txt";
		model.addObject("page", page);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		model.addObject("docpath", filePath);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages/upload")
	public ModelAndView uploadPage(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @RequestParam String curuid, @RequestParam String uid, @RequestParam String auth, @RequestParam String pagename, @RequestParam String summary, @RequestParam CommonsMultipartFile fileupload){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(curuid));
		}
		catch(Exception e){
			user = dbService.getUserByName(curuid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		Page page = dbService.createPage(target.getUsername(),content.getFullId(),category.getFullId(),project.getFullId(),pagename,summary,fileupload);
		if(page == null){
			RequestError error5 = new RequestError(404, "Unable to create page");
			page = new Page(error5);
		}
		ModelAndView model;
		if(project.getAllowedviewers() != null && !project.getUids().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("page_create_result");
		}
		model.addObject("page", page);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", curuid);
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages/{pnum}/reviews")
	public ModelAndView getReviews(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @PathVariable String pnum, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		Page page = null;
		try{
			page = dbService.getPageByID(project.getFullId() + "PAGE" + Integer.parseInt(pnum));
		}catch(Exception e){
			List<Page> pageList = dbService.getPagesByProject(project.getFullId());
			for(Page p : pageList){
				if(p.getName() == pnum){
					page = p;
				}
			}
		}
		if(page == null){
			RequestError error5 = new RequestError(404, "Unable to read page");
			page = new Page(error5);
		}
		List<Review> rList = dbService.getReviewsByPage(page.getFullId());
		RequestError error6 = null;
		if(rList == null){
			error6 = new RequestError(404, "Unable to read reviews.json");
		}
		Reviews reviews = new Reviews(rList,error6);
		ModelAndView model;
		if(project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("review_list");
		}
		model.addObject("reviews", reviews);
		model.addObject("page", page);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages/{pnum}/write_review")
	public ModelAndView writeReview(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @PathVariable String pnum, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		Page page = null;
		try{
			page = dbService.getPageByID(project.getFullId() + "PAGE" + Integer.parseInt(pnum));
		}catch(Exception e){
			List<Page> pageList = dbService.getPagesByProject(project.getFullId());
			for(Page p : pageList){
				if(p.getName() == pnum){
					page = p;
				}
			}
		}
		if(page == null){
			RequestError error5 = new RequestError(404, "Unable to read page");
			page = new Page(error5);
		}
		ModelAndView model;
		if((project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())) || (!project.getReviewable() && user.getId() == 0)){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("create_review");
		}
		model.addObject("page", page);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages/{pnum}/review_fail")
	public ModelAndView failReview(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @PathVariable String pnum, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		Page page = null;
		try{
			page = dbService.getPageByID(project.getFullId() + "PAGE" + Integer.parseInt(pnum));
		}catch(Exception e){
			List<Page> pageList = dbService.getPagesByProject(project.getFullId());
			for(Page p : pageList){
				if(p.getName() == pnum){
					page = p;
				}
			}
		}
		if(page == null){
			RequestError error5 = new RequestError(404, "Unable to read page");
			page = new Page(error5);
		}
		ModelAndView model;
		//if((project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())) || (!project.getReviewable() && user.getId() != 0)){
		//	model = new ModelAndView("not_authorized_to_view_project");
		//}
		//else{
			model = new ModelAndView("failed_review");
		//}
		model.addObject("page", page);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
	
	@RequestMapping(value="/users/{name}/contents/{con}/categories/{cat}/projects/{proj}/pages/{pnum}/reviews/{revnum}")
	public ModelAndView getReview(@PathVariable String name, @PathVariable String con, @PathVariable String cat, @PathVariable String proj, @PathVariable String pnum, @PathVariable String revnum, @RequestParam(value="uid", required=false, defaultValue="0") String uid, @RequestParam(value="auth", required=false, defaultValue="null") String auth){
		User user;
		try{
			user = dbService.getUserByID(Integer.parseInt(uid));
		}
		catch(Exception e){
			user = dbService.getUserByName(uid);
		}
		if(user == null || !auth.equals(Integer.toHexString(user.hashCode()))){
			user = dbService.getUserByID(0);
			if(user == null){
				RequestError error = new RequestError(404, "ERROR WITH users.json!");
				user = new User(error);
				user.setUsername("Broken users.json!");
			}
		}
		Content content;
		User target;
		Category category;
		try{
			
			target = dbService.getUserByID(Integer.parseInt(name));
		}
		catch(Exception e){

			target = dbService.getUserByName(name);
		}
		if(target == null){
			RequestError error2 = new RequestError(404, "The requested user " + name + " is out of range or invalid.");
			target = new User(error2);
		}
		try{
			content = dbService.getContentByID("U" + target.getId() + "CON" + Integer.parseInt(con));
		}catch(Exception e){
			if(target.getId()==0){
				content = dbService.getContentByName(con);
			}else{
				content = dbService.getContentByName(target.getUsername() + "'s " + con);
			}
		}
		if(content == null){
			RequestError error = new RequestError(404, "Unable to read the contents.");
			content = new Content(error);
		}
		try{
			category = dbService.getCategoryByID(content.getFullId() + "CAT" + Integer.parseInt(cat));
		}catch(Exception e){
			if(target.getId()==0){
				category = dbService.getCategoryByName(cat);
			}else{
				category = dbService.getCategoryByName(target.getUsername() + "'s " + cat);
			}
		}
		if(category == null){
			RequestError error3 = new RequestError(404, "Unable to read the category");
			category = new Category(error3);
		}
		Project project = null;
		try{
			project = dbService.getProjectByID(category.getFullId() + "PROJ" + Integer.parseInt(proj));
		}catch(Exception e){
			List<Project> projList = dbService.getProjectsByCategory(category.getFullId());
			for(Project p : projList){
				if(p.getName() == proj){
					project = p;
				}
			}
		}
		if(project == null){
			RequestError error4 = new RequestError(404, "Unable to read project");
			project = new Project(error4);
		}
		Page page = null;
		try{
			page = dbService.getPageByID(project.getFullId() + "PAGE" + Integer.parseInt(pnum));
		}catch(Exception e){
			List<Page> pageList = dbService.getPagesByProject(project.getFullId());
			for(Page p : pageList){
				if(p.getName() == pnum){
					page = p;
				}
			}
		}
		if(page == null){
			RequestError error5 = new RequestError(404, "Unable to read page");
			page = new Page(error5);
		}
		Review review = dbService.getReviewByID(page.getFullId() + "R" + revnum);
		if(review == null){
			RequestError error6 = new RequestError(404, "Unable to read reviews.json");
			review = new Review(error6);
		}
		ModelAndView model;
		if(project.getAllowedviewers() != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(user.getId())){
			model = new ModelAndView("not_authorized_to_view_project");
		}
		else{
			model = new ModelAndView("single_review");
		}
		model.addObject("review", review);
		model.addObject("page", page);
		model.addObject("project", project);
		model.addObject("category",category);
		model.addObject("content",content);
		model.addObject("user",target);
		model.addObject("name", user.getUsername());
		model.addObject("auth", auth);
		return model;
	}
}
