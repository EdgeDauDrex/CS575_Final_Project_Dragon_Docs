<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Creation</title>
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
<c:if test="${user.error == null && content.error == null && category.error == null && user.username != name || user.id == 0}">
<div>
<h3>You are not authorized to create a project here.</h3>
</div>
</c:if>
<c:if test="${user.error == null && content.error == null && category.error == null && user.username == name && user.id != 0}">
<div>
<h3>User : ${user.username}</h3>
<center>
    <h4>Project Details</h4>
    <form action="/DragonDocs/projectSubmit.jsp" method="post">
         <br/>Project Name:<input type="text" name="projectname">
         <br>
         <br/>Username:<input type="text" name="username" readonly="readonly" value="${user.username}">
         <br>
         <br/>Content Level:<input type="text" name="contentname" readonly="readonly" value="${content.name}">
         <br>
         <br/>Category:<input type="text" name="catname" readonly="readonly" value="${category.name}">
         <br>
         <br/>Publicly Viewable And Reviewable:<input type="radio" name="audience" value="publicVpublicR">
         <br/>Publicly Viewable Privately Reviewable:<input type="radio" name="audience" value="publicVprivateR">
         <br/>Privately Viewable and Reviewable:<input type="radio" name="audience" value="privateVprivateR">
         <br>
         <br/>Summary:<textarea name="summary" rows="10" cols="200" wrap="hard"></textarea>
         <br>
         <br/>Auth:<input type="password" name="auth" readonly="readonly" value="${auth}">
         <br/><input type="submit" value="Submit">
    </form>
</center>
</div>
</c:if>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects?uid=${name}&auth=${auth}">Back</a>
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