<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dragon Docs Homepage</title>
</head>
<body>
<h1>Welcome to Dragon Docs!</h1>
<h2>You are currently here as : ${name}</h2>
<a href="/DragonDocs/new_user">Join Us</a>
<br>
<br>
<a href="/DragonDocs/users?uid=${name}&auth=${auth}">Users</a>
<br>
<a href="/DragonDocs/projects?uid=${name}&auth=${auth}">All Projects</a>
<br>
<br>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">Your Profile</a>
</body>
</html>
