<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Created</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>User Created Or Updated Successfully!</h2>
<h3>You are currently here as : ${name}</h3>
<h4>Your current authentication information is : ?uid=${name}&auth=${auth}</h4>
<h5>This will change if you update your user profile.</h5>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">To Profile</a>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>