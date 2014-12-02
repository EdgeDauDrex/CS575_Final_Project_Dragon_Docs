<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/views/include.jsp" %>
    <%@page import="edu.drexel.cs575.data.DB"%>
    <%@page import="edu.drexel.cs575.model.Project" %>
<%@page import="edu.drexel.cs575.model.Content" %>
<%@page import="edu.drexel.cs575.model.Category" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Project</title>
</head>
<body>
<%
		String contentName=request.getParameter("contentname");
		String categoryName=request.getParameter("catname");
		String username=request.getParameter("username");
        String projectName=request.getParameter("projectname");
        String audience=request.getParameter("audience");
        String summary=request.getParameter("summary");
        String auth = request.getParameter("auth");
        String contrib = request.getParameter("new_users");
        String myname = request.getParameter("myname");
        DB db = new DB();
        Content content = db.getContentByName(contentName);
        Category category = db.getCategoryByName(categoryName);
        Project project = db.getProjectByID(projectName);
        project = db.updateProject(project,summary,contrib,audience);
        if(project != null){
        	response.sendRedirect("/DragonDocs/users/"+username+"/contents/"+content.getId()+"/categories/"+category.getId()+"/projects/"+project.getId()+"?uid="+myname+"&auth="+auth);
        }else{
        	response.sendRedirect("/DragonDocs/users/"+username+"/contents/"+content.getId()+"/categories/"+category.getId()+"/projects/project_fail?uid="+myname+"&auth="+auth);
        }
    %>
</body>
</html>