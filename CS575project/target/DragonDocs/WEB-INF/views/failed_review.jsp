<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Failed to Review</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>Failed to Review</h2>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
<br>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages/${page.id}/reviews?uid=${name}&auth=${auth}">Reviews Page</a>
</body>
</html>