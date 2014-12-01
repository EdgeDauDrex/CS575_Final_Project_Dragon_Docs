<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Content Page</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<c:if test="${user.error != null}">
<div><h3>Error : ${user.error}</h3></div>
<br>
</c:if>
<c:if test="${content.error != null}">
<div><h3>Error : ${content.error}</h3></div>
<br>
</c:if>
<c:if test="${user.error == null && content.error == null}">
<div>
<h3>${content.name}</h3>
<br>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories?uid=${name}&auth=${auth}">Content Categories</a>
<br>
<br>
<a href="/DragonDocs/users/${user.username}/contents?uid=${name}&auth=${auth}">Back</a>
<br>
<br>
<a href="/DragonDocs/users/${user.username}?uid=${name}&auth=${auth}">${user.username}'s Profile</a>
</div>
</c:if>
<br>
<br>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">Your Profile</a>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>