package edu.drexel.cs575.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.drexel.cs575.model.Category;
import edu.drexel.cs575.model.Content;
import edu.drexel.cs575.model.Page;
import edu.drexel.cs575.model.Project;
import edu.drexel.cs575.model.Review;
import edu.drexel.cs575.model.User;

/**
 * RESTFul web service database - note that this is mocked for a local machine
 * but with only minimial work should function properly on a web server
 * 
 * @version 1.0
 */
@Service
public class DB {

	private final String PRIMARY_STORAGE = "C:\\DragonDocs\\";
	private final String[] BACKUPS = {"F:\\DragonDocs\\"};
	
	public User createUser(String uName, String password){
		List<User> users = getUsers();
		for(User u: users){
			if (u.getUsername()==uName){
				return null;
			}
		}
		User newUser = new User();
		newUser.setId(users.size());
		newUser.setUsername(uName);
		newUser.setPassword(password);
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		newUser.setJoinDate(month + "/" + day + "/" + year);
		newUser.setUpdateDate(month + "/" + day + "/" + year);
		newUser.setBio("");
		Content newFun = new Content(1,"U"+newUser.getId()+"CON1",uName+"'s Fun",newUser.getId());
		Content newWork = new Content(2,"U"+newUser.getId()+"CON2",uName+"'s Work",newUser.getId());
		Category newWalk = new Category(1,"U"+newUser.getId()+"CON1CAT1",uName+"'s Walkthroughs",newUser.getId(),"U"+newUser.getId()+"CON1");
		Category newFanFic = new Category(2,"U"+newUser.getId()+"CON1CAT2",uName+"'s Fanfics",newUser.getId(),"U"+newUser.getId()+"CON1");
		Category newFiction = new Category(3,"U"+newUser.getId()+"CON1CAT3",uName+"'s Original Fictions",newUser.getId(),"U"+newUser.getId()+"CON1");
		Category newPoems = new Category(4,"U"+newUser.getId()+"CON1CAT4",uName+"'s Poems",newUser.getId(),"U"+newUser.getId()+"CON1");
		Category newScripts = new Category(5,"U"+newUser.getId()+"CON1CAT5",uName+"'s Scripts",newUser.getId(),"U"+newUser.getId()+"CON1");
		Category newRecipes = new Category(6,"U"+newUser.getId()+"CON1CAT6",uName+"'s Recipes",newUser.getId(),"U"+newUser.getId()+"CON1");
		Category newCV = new Category(1,"U"+newUser.getId()+"CON2CAT1",uName+"'s CVs and Resumes",newUser.getId(),"U"+newUser.getId()+"CON2");
		Category newPapers = new Category(2,"U"+newUser.getId()+"CON2CAT2",uName+"'s Papers",newUser.getId(),"U"+newUser.getId()+"CON2");
		Category newCode = new Category(3,"U"+newUser.getId()+"CON2CAT3",uName+"'s Code",newUser.getId(),"U"+newUser.getId()+"CON2");
		Category newArticles = new Category(4,"U"+newUser.getId()+"CON2CAT4",uName+"'s Articles",newUser.getId(),"U"+newUser.getId()+"CON2");
		ObjectMapper mapper = new ObjectMapper();
		File jsonDB;
		try {
			jsonDB = new ClassPathResource("users.json").getFile();
			List<User> userList = mapper.readValue(jsonDB, new TypeReference<List<User>>(){});
			userList.add(newUser);
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,userList);
			jsonDB = new ClassPathResource("contents.json").getFile();
			List<Content> contentList = mapper.readValue(jsonDB, new TypeReference<List<Content>>(){});
			contentList.add(newFun);
			contentList.add(newWork);
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,contentList);
			jsonDB = new ClassPathResource("categories.json").getFile();
			List<Category> categoryList = mapper.readValue(jsonDB, new TypeReference<List<Category>>(){});
			categoryList.add(newWalk);
			categoryList.add(newFanFic);
			categoryList.add(newFiction);
			categoryList.add(newPoems);
			categoryList.add(newScripts);
			categoryList.add(newRecipes);
			categoryList.add(newCV);
			categoryList.add(newPapers);
			categoryList.add(newCode);
			categoryList.add(newArticles);
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,categoryList);
			File newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newWalk.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newFanFic.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newFiction.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newPoems.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newScripts.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newRecipes.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newCV.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newPapers.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newCode.getFullId());
			newFolder.mkdirs();
			newFolder = new File(PRIMARY_STORAGE + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newArticles.getFullId());
			newFolder.mkdirs();
			try{
			for(int i = 0; i < BACKUPS.length; i++){
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newWalk.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newFanFic.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newFiction.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newPoems.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newScripts.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newFun.getFullId() + "\\" + newRecipes.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newCV.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newPapers.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newCode.getFullId());
				newFolder.mkdirs();
				newFolder = new File(BACKUPS[i] + newUser.getUsername() + "\\" + newWork.getFullId() + "\\" + newArticles.getFullId());
				newFolder.mkdirs();
			}
			}catch(Exception e){
				System.out.println("Backup Failure!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return newUser;
	}
	
	public Project createProject(String username, String contentname, String categoryname, String projectName, String audience, String summary){
		Project project = null;
		User user = getUserByName(username);
		Content content = getContentByName(contentname);
		Category category = getCategoryByName(categoryname);
		List<Project> pList = getProjectsByCategory(category.getFullId());
		int pid;
		if(pList == null){
			pid = 0;
		}
		else{
			pid = pList.size();
		}
		String fullId = category.getFullId() + "PROJ" + pid;
		List<Integer> uids = new ArrayList<Integer>();
		uids.add(user.getId());
		boolean reviewable;
		List<Integer> viewers = new ArrayList<Integer>();
		if(audience.equals("publicVpublicR")){
			reviewable = true;
			viewers.add(0);
		}else if(audience.equals("publicVprivateR")){
			reviewable = false;
			viewers.add(0);
		}else if(audience.equals("privateVprivateR")){
			reviewable = false;
			viewers.add(user.getId());
		}else{
			reviewable = false;
			viewers.add(0);
		}
		summary = summary.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		project = new Project(pid, projectName, summary, uids, category.getFullId(), month + "/" + day + "/" + year, month + "/" + day + "/" + year, reviewable, viewers, fullId);
		ObjectMapper mapper = new ObjectMapper();
		File jsonDB;
		try {
			jsonDB = new ClassPathResource("projects.json").getFile();
			List<Project> projectList = mapper.readValue(jsonDB, new TypeReference<List<Project>>(){});
			projectList.add(project);
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,projectList);
			File newProject = new File(PRIMARY_STORAGE + user.getUsername() + "\\" + content.getFullId() + "\\" + category.getFullId() + "\\" + fullId);
			newProject.mkdirs();
			try{
			for(int i = 0; i<BACKUPS.length; i++){
				newProject = new File(BACKUPS[i] + user.getUsername() + "\\" + content.getFullId() + "\\" + category.getFullId() + "\\" + fullId);
				newProject.mkdirs();
			}
			}catch(Exception e){
				System.out.println("Backup failure!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return project;
	}
	
	public Project updateProject(Project project, String summary, String users, String viewers){
		List<Project> projects = getProjects();
		if(!projects.contains(project)){
			return null;
		}
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		project.setUpdateDate(month + "/" + day + "/" + year);
		summary = summary.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
		project.setSummary(summary);
		User u;
		List<Integer> updatingList, updatingList2;
		if(users != null && !users.equals("")){
			users = users.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
			String[] splitUsers = users.split("<br/>");
			updatingList = project.getUids();
			updatingList2 = project.getAllowedviewers();
			for(int i = 0; i < splitUsers.length; i++){
				u = getUserByName(splitUsers[i]);
				if(u != null && !project.getUids().contains(u.getId())){
					updatingList.add(u.getId());
					if(!project.getAllowedviewers().contains(u.getId()) && !project.getAllowedviewers().contains(new Integer(0))){
						updatingList.add(u.getId());
					}
				}
			}
			project.setUids(updatingList);
			project.setAllowedviewers(updatingList2);
		}
		if(viewers != null && !viewers.equals("")){
			viewers = viewers.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
			String[] splitViewers = viewers.split("<br/>");
			updatingList = project.getAllowedviewers();
			for(int i =0; i < splitViewers.length; i++){
				u = getUserByName(splitViewers[i]);
				if(u != null && !project.getAllowedviewers().contains(new Integer(0)) && !project.getAllowedviewers().contains(u.getId())){
					updatingList.add(u.getId());
				}
			}
			project.setAllowedviewers(updatingList);
		}
		ObjectMapper mapper = new ObjectMapper();
		File jsonDB;
		try {
			jsonDB = new ClassPathResource("projects.json").getFile();
			for(Project p : projects){
				if(p.getId() == project.getId()){
					projects.add(projects.indexOf(p), project);
					projects.remove(p);
				}
			}
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,projects);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return project;
	}
	
	public User updateUser(User user, String password, String bio){
		List<User> users = getUsers();
		if(!users.contains(user)){
			return null;
		}
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		user.setUpdateDate(month + "/" + day + "/" + year);
		user.setPassword(password);
		bio = bio.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
		user.setBio(bio);
		ObjectMapper mapper = new ObjectMapper();
		File jsonDB;
		try {
			jsonDB = new ClassPathResource("users.json").getFile();
			for(User u : users){
				if(u.getId() == user.getId()){
					users.add(users.indexOf(u), user);
					users.remove(u);
				}
			}
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	public Review createReview(String reviewer, String pageId, String rev){
		Review review = null;
		List<Review> reviews = getReviewsByPage(pageId);
		User user = getUserByName(reviewer);
		Page p = getPageByID(pageId);
		int rid;
		if(reviews == null){
			rid = 0;
		}
		else{
			rid = reviews.size();
		}
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		String revDate = month + "/" + day + "/" + year;
		String fullId = p.getFullId()+"R"+rid;
		rev = rev.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
		review = new Review(rid,fullId,user.getId(),reviewer,rev,revDate,pageId);
		ObjectMapper mapper = new ObjectMapper();
		File jsonDB;
		try {
			jsonDB = new ClassPathResource("reviews.json").getFile();
			List<Review> reviewList = mapper.readValue(jsonDB, new TypeReference<List<Review>>(){});
			reviewList.add(review);
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,reviewList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return review;
	}
	
	public HashMap<String, Content> getContentCache() {
		return queryContents();
	}

	public HashMap<String, Category> getCategoryCache() {
		return queryCategories();
	}

	public HashMap<String, Project> getProjectCache() {
		return queryProjects();
	}

	public HashMap<String, Page> getPageCache() {
		return queryPages();
	}
	
	public HashMap<String, Review> getReviewCache() {
		return queryReviews();
	}

	public HashMap<Integer, User> getUserCache() {
		return queryUsers();
	}
	
	public List<User> getUsers(){
		Collection<User> userCollect= queryUsers().values();
		List<User> users = new ArrayList<User>(userCollect);
		return users;
	}
	
	public List<Review> getReviews(){
		Collection<Review> revCollect= queryReviews().values();
		List<Review> reviews = new ArrayList<Review>(revCollect);
		return reviews;
	}
	
	public List<Content> getContents(){
		Collection<Content> contentCollect= queryContents().values();
		List<Content> contents = new ArrayList<Content>(contentCollect);
		return contents;
	}
	
	public List<Category> getCategories(){
		Collection<Category> catCollect= queryCategories().values();
		List<Category> categories = new ArrayList<Category>(catCollect);
		return categories;
	}
	
	public List<Project> getProjects(){
		Collection<Project> projCollect= queryProjects().values();
		List<Project> projects = new ArrayList<Project>(projCollect);
		return projects;
	}
	
	public List<Project> getProjectsByUser(int id){
		return queryProjectsByUser().get(id);
	}
	
	public List<Project> getProjectsByCategory(String id){
		return queryProjectsByCategory().get(id);
	}
	
	public List<Page> getPages(){
		Collection<Page> pageCollect= queryPages().values();
		List<Page> pages = new ArrayList<Page>(pageCollect);
		return pages;
	}
	
	public List<Content> getContentsByUser(int id){
		Integer ind = new Integer(id);
		return queryContentsByUser().get(ind);
	}
	
	public List<Content> getContentsByUsername(String name){
		return queryContentsByUser().get(queryUsersNames().get(name));
	}
	
	public List<Category> getCategoriesByContent(String conid){
		return queryCategoriesByContent().get(conid);
	}
	
	public List<Category> getCategoriesByContentname(String name){
		return queryCategoriesByContent().get(queryContentsNames().get(name));
	}
	
	public List<Page> getPagesByProject(String name){
		return queryPagesByProject().get(name);
	}
	
	public List<Review> getReviewsByPage(String name){
		return queryReviewsByPage().get(name);
	}
	
	public User getUserByID(int i){
		Integer ind = new Integer(i);
		HashMap<Integer,User> users = queryUsers();
		return users.get(ind);
	}
	
	public User getUserByName(String s){
		return queryUsers().get(queryUsersNames().get(s));
	}
	
	public Project getProjectByID(String s){
		return queryProjects().get(s);
	}
	
	public Review getReviewByID(String s){
		return queryReviews().get(s);
	}
	
	public Category getCategoryByID(String s){
		return queryCategories().get(s);
	}
	
	public Category getCategoryByName(String s){
		return queryCategories().get(queryCategoriesNames().get(s));
	}
	
	public Page getPageByID(String s){
		return queryPages().get(s);
	}
	
	public Content getContentByID(String s){
		return queryContents().get(s);
	}
	
	public Content getContentByName(String s){
		return queryContents().get(queryContentsNames().get(s));
	}
	
	private HashMap<Integer, User> queryUsers(){
		//if (userCache != null && changedUsers) return userCache;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<Integer, User> userDB = new HashMap<Integer, User>();		
		try{
			File jsonDB = new ClassPathResource("users.json").getFile();
			List<User> userList = mapper.readValue(jsonDB, new TypeReference<List<User>>(){});
			for (User u : userList){
				Integer idx = new Integer(u.getId());
				userDB.put(idx, u);
			}
			return userDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<Integer, List<Content>> queryContentsByUser(){
		//if (contentsByUser != null && changedContents && changedUsers) return contentsByUser;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<Integer, List<Content>> contentDB = new HashMap<Integer, List<Content>>();		
		try{
			File jsonDB = new ClassPathResource("contents.json").getFile();
			List<Content> contentList = mapper.readValue(jsonDB, new TypeReference<List<Content>>(){});
			for (Content c : contentList){
				Integer idx = new Integer(c.getUid());
				if(!contentDB.containsKey(idx)){
					contentDB.put(idx,new ArrayList<Content>());
				}
				contentDB.get(idx).add(c);
			}
			return contentDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<Integer, List<Project>> queryProjectsByUser(){
		//if (projectsByUser != null && changedProjects && changedUsers) return projectsByUser;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<Integer, List<Project>> projectDB = new HashMap<Integer, List<Project>>();		
		try{
			File jsonDB = new ClassPathResource("projects.json").getFile();
			List<Project> projectList = mapper.readValue(jsonDB, new TypeReference<List<Project>>(){});
			for (Project p : projectList){
				List<Integer> ids = p.getUids();
				for(Integer i : ids){
					if(!projectDB.containsKey(i)){
						projectDB.put(i,new ArrayList<Project>());
					}
					projectDB.get(i).add(p);
				}
			}
			return projectDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, List<Project>> queryProjectsByCategory(){
		//if (projectsByCategory != null && changedProjects && changedCategories) return projectsByCategory ;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, List<Project>> projectDB = new HashMap<String, List<Project>>();		
		try{
			File jsonDB = new ClassPathResource("projects.json").getFile();
			List<Project> projectList = mapper.readValue(jsonDB, new TypeReference<List<Project>>(){});
			for (Project p : projectList){
				String id = p.getCatid();
				if(!projectDB.containsKey(id)){
					projectDB.put(id,new ArrayList<Project>());
				}
				projectDB.get(id).add(p);
			}
			return projectDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, List<Category>> queryCategoriesByContent(){
		//if (categoriesByContent != null && changedContents && changedCategories) return categoriesByContent;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, List<Category>> categoryDB = new HashMap<String, List<Category>>();		
		try{
			File jsonDB = new ClassPathResource("categories.json").getFile();
			List<Category> categoryList = mapper.readValue(jsonDB, new TypeReference<List<Category>>(){});
			for (Category c : categoryList){
				String idx = c.getConId();
				if(!categoryDB.containsKey(idx)){
					categoryDB.put(idx,new ArrayList<Category>());
				}
				categoryDB.get(idx).add(c);
			}
			return categoryDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, String> queryContentsNames() {
		//if (contentByName != null && changedContents) return contentByName;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> contentDB = new HashMap<String, String>();
		try{
			File jsonDB = new ClassPathResource("contents.json").getFile();
			List<Content> contentList = mapper.readValue(jsonDB, new TypeReference<List<Content>>(){});
			for (Content c : contentList){
				contentDB.put(c.getName(), c.getFullId());
			}
			return contentDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, Integer> queryUsersNames(){
		//if (userByName != null && changedUsers) return userByName;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Integer> userDB = new HashMap<String, Integer>();		
		try{
			File jsonDB = new ClassPathResource("users.json").getFile();
			List<User> userList = mapper.readValue(jsonDB, new TypeReference<List<User>>(){});
			for (User u : userList){
				userDB.put(u.getUsername(), u.getId());
			}
			return userDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, Page> queryPages() {
		//if (pageCache != null && changedPages) return pageCache;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Page> pageDB = new HashMap<String, Page>();		
		try{
			File jsonDB = new ClassPathResource("pages.json").getFile();
			List<Page> PageList = mapper.readValue(jsonDB, new TypeReference<List<Page>>(){});
			for (Page p : PageList){
				String idx = new String(p.getFullId());
				pageDB.put(idx, p);
			}
			return pageDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private HashMap<String, Content> queryContents() {
		//if (contentCache != null && changedContents) return contentCache;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Content> contentDB = new HashMap<String, Content>();		
		try{
			File jsonDB = new ClassPathResource("contents.json").getFile();
			List<Content> ContentList = mapper.readValue(jsonDB, new TypeReference<List<Content>>(){});
			for (Content c : ContentList){
				String idx = new String(c.getFullId());
				contentDB.put(idx, c);
			}
			return contentDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, Category> queryCategories() {
		//if (categoryCache != null && changedCategories) return categoryCache;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Category> categoryDB = new HashMap<String, Category>();		
		try{
			File jsonDB = new ClassPathResource("categories.json").getFile();
			List<Category> categoryList = mapper.readValue(jsonDB, new TypeReference<List<Category>>(){});
			for (Category c : categoryList){
				String idx = new String(c.getFullId());
				categoryDB.put(idx, c);
			}
			return categoryDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, String> queryCategoriesNames() {
		//if (categoryByName != null && changedCategories) return categoryByName;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> categoryDB = new HashMap<String, String>();		
		try{
			File jsonDB = new ClassPathResource("categories.json").getFile();
			List<Category> categoryList = mapper.readValue(jsonDB, new TypeReference<List<Category>>(){});
			for (Category c : categoryList){
				String idx = new String(c.getName());
				categoryDB.put(idx, c.getFullId());
			}
			return categoryDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, Project> queryProjects() {
		//if (projectCache != null && changedProjects) return projectCache;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Project> projectDB = new HashMap<String, Project>();		
		try{
			File jsonDB = new ClassPathResource("projects.json").getFile();
			List<Project> projectList = mapper.readValue(jsonDB, new TypeReference<List<Project>>(){});
			for (Project p : projectList){
				String idx = new String(p.getFullId());
				projectDB.put(idx, p);
			}
			return projectDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private HashMap<String, Review> queryReviews() {
		//if (reviewCache != null && changedReviews) return reviewCache;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Review> reviewDB = new HashMap<String, Review>();		
		try{
			File jsonDB = new ClassPathResource("reviews.json").getFile();
			List<Review> reviewList = mapper.readValue(jsonDB, new TypeReference<List<Review>>(){});
			for (Review r : reviewList){
				String idx = new String(r.getFullId());
				reviewDB.put(idx, r);
			}
			return reviewDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, List<Page>> queryPagesByProject(){
		//if (pagesByProject != null && changedProjects && changedPages) return pagesByProject ;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, List<Page>> pageDB = new HashMap<String, List<Page>>();		
		try{
			File jsonDB = new ClassPathResource("pages.json").getFile();
			List<Page> pageList = mapper.readValue(jsonDB, new TypeReference<List<Page>>(){});
			for (Page p : pageList){
				String id = p.getPid();
				if(!pageDB.containsKey(id)){
					pageDB.put(id,new ArrayList<Page>());
				}
				pageDB.get(id).add(p);
			}
			return pageDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private HashMap<String, List<Review>> queryReviewsByPage(){
		//if (reviewsByPage != null && changedReviews && changedPages) return reviewsByPage ;
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, List<Review>> reviewDB = new HashMap<String, List<Review>>();		
		try{
			File jsonDB = new ClassPathResource("reviews.json").getFile();
			List<Review> reviewList = mapper.readValue(jsonDB, new TypeReference<List<Review>>(){});
			for (Review r : reviewList){
				String id = r.getPid();
				if(!reviewDB.containsKey(id)){
					reviewDB.put(id,new ArrayList<Review>());
				}
				reviewDB.get(id).add(r);
			}
			return reviewDB;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String getPRIMARY_STORAGE() {
		return PRIMARY_STORAGE;
	}

	public String[] getBACKUPS() {
		return BACKUPS;
	}

	public Page createPage(String username, String contentId, String categoryId, String projectId, 
			String pagename, String summary, CommonsMultipartFile fileupload) {
		User user = getUserByName(username);
		Content content = getContentByID(contentId);
		Category category = getCategoryByID(categoryId);
		Project project = getProjectByID(projectId);
		Page page = null;
		List<Page> pList = getPagesByProject(projectId);
		int pid;
		if(pList == null){
			pid = 0;
		}
		else{
			pid = pList.size();
		}
		String fullId = project.getFullId() + "PAGE" + pid;
		Integer uid = user.getId();
		String projName = project.getName();
		summary = summary.replaceAll("(\r\n|\n\r|\r|\n|<br>|<br />)", "<br/>");
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		String cDate = month + "/" + day + "/" + year;
		String uDate = month + "/" + day + "/" + year;
		page = new Page(pid,fullId,pagename,projName,project.getFullId(),category.getFullId(),uid,summary,cDate,uDate);
		ObjectMapper mapper = new ObjectMapper();
		File jsonDB;
		try {
			jsonDB = new ClassPathResource("pages.json").getFile();
			List<Page> pageList = mapper.readValue(jsonDB, new TypeReference<List<Page>>(){});
			pageList.add(page);
			mapper.writerWithDefaultPrettyPrinter().writeValue(jsonDB,pageList);
			String saveDirectory = user.getUsername() + "\\" + content.getFullId() + "\\" + category.getFullId() + "\\" + project.getFullId() + "\\" + page.getFullId() + ".txt";
			File mainFile = new File(PRIMARY_STORAGE + saveDirectory);
			fileupload.transferTo(mainFile);
			File backFile;
			try{
			for(int i = 0; i<BACKUPS.length; i++){
				backFile = new File(BACKUPS[i] + saveDirectory);
				java.nio.file.Files.copy(mainFile.toPath(), backFile.toPath());
			}
			}catch(Exception e){
				System.out.println("Backup error");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return page;
	}
	
	
}
