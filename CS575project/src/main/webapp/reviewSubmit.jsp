<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/views/include.jsp" %>
    <%@page import="edu.drexel.cs575.data.DB"%>
<%@page import="edu.drexel.cs575.model.Project" %>
<%@page import="edu.drexel.cs575.model.Content" %>
<%@page import="edu.drexel.cs575.model.Category" %>
<%@page import="edu.drexel.cs575.model.Page" %>
<%@page import="edu.drexel.cs575.model.Review" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Submit Review</title>
</head>
<body>
<%
		String contentName=request.getParameter("contentname");
		String categoryName=request.getParameter("catname");
		String authorname=request.getParameter("authorname");
        String projectName=request.getParameter("projectname");
        String review=request.getParameter("review");
        String auth = request.getParameter("auth");
        String reviewer = request.getParameter("reviewer");
        String pageId = request.getParameter("pageId");
        String projectId = request.getParameter("projectId");
        String pageName = request.getParameter("pagename");
        DB db = new DB();
        Page reviewedPage = db.getPageByID(pageId);
        Review pageReview = db.createReview(reviewer,pageId,review);
        Project project = db.getProjectByID(projectId);
        Content content = db.getContentByName(contentName);
        Category category = db.getCategoryByName(categoryName);
        if(pageReview != null && reviewedPage != null && project != null && content != null && category != null){
        	response.sendRedirect("/DragonDocs/users/"+authorname+"/contents/"+content.getId()+"/categories/"+category.getId()+"/projects/"+project.getId()+"/pages/"+reviewedPage.getId()+"/reviews/"+pageReview.getId()+"?uid="+reviewer+"&auth="+auth);
        }else if(pageReview == null && reviewedPage != null && project != null && content != null && category != null){
        	response.sendRedirect("/DragonDocs/users/"+authorname+"/contents/"+content.getId()+"/categories/"+category.getId()+"/projects/"+project.getId()+"/pages/"+reviewedPage.getId()+"/review_fail?uid="+reviewer+"&auth="+auth);
        }else{
        	response.sendRedirect("/DragonDocs/Error.jsp");
        }
    %>   
</body>
</html>