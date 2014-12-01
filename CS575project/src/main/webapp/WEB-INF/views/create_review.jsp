<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Review</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>Create a new Project</h2>
<c:if test="${user.error != null}">
<div><h3>Error : ${user.error}</h3></div>
<br>
</c:if>
<c:if test="${content.error != null}">
<div><h3>Error : ${conent.error}</h3></div>
<br>
</c:if>
<c:if test="${category.error != null}">
<div><h3>Error : ${category.error}</h3></div>
<br>
</c:if>
<c:if test="${user.error == null && content.error == null && category.error == null}">
<div>
<h3>Page : ${page.name}</h3>
<center>
    <h4>Review</h4>
    <form action="/DragonDocs/reviewSubmit.jsp" method="post">
    	 <br>/Page Name:<input type="text" name="pagename" readonly="readonly" value="${page.name}">
    	 <br>
         <br/>Project Name:<input type="text" name="projectname" readonly="readonly" value="${project.name}">
         <br>
         <br/>Page Author:<input type="text" name="authorname" readonly="readonly" value="${user.username}">
         <br>
         <br/>Content Level:<input type="text" name="contentname" readonly="readonly" value="${content.name}">
         <br>
         <br/>Category:<input type="text" name="catname" readonly="readonly" value="${category.name}">
         <br>
         <br/>Username:<input type="text" name="reviewer" readonly="readonly" value="${name}">
         <br>
         <br/>Review:<textarea name="review" rows="10" cols="200" wrap="hard"></textarea>
         <br>
         <br/>Auth:<input type="password" name="auth" readonly="readonly" value="${auth}">
         <br>
         <br/>Page ID:<input type="password" name="pageId" readonly="readonly" value="${page.fullId}">
         <br>
         <br/>Project ID:<input type="password" name="projectId" readonly="readonly" value="${project.fullId}">
         <br>
         <br/><input type="submit" value="Submit">
    </form>
</center>
</div>
</c:if>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages/${page.id}?uid=${name}&auth=${auth}">Back to ${page.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages/${page.id}/reviews?uid=${name}&auth=${auth}">Back to Review List</a>
<br>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages?uid=${name}&auth=${auth}">Pages</a>
<br>	
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}?uid=${name}&auth=${auth}">${project.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects?uid=${name}&auth=${auth}">Projects Page</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}?uid=${name}&auth=${auth}">${category.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories?uid=${name}&auth=${auth}">Categories Page</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}?uid=${name}&auth=${auth}">${content.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents?uid=${name}&auth=${auth}">${user.username}'s Contents Page</a>
<br>
<a href="/DragonDocs/users/${user.username}?uid=${name}&auth=${auth}">${user.username}'s Profile</a>
<br>
<br>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">Your Profile</a>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>