<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dragon Docs Signup</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>Create a new Account</h2>
<center>
    <h3>Signup Details</h3>
    <form action="signupSubmit.jsp" method="post">
         <br/>Username:<input type="text" name="username">
         <br>
         <br/>Password:<input type="password" name="password">
         <br/><input type="submit" value="Submit">
    </form>
</center>
<br>
<br>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">Your Profile</a>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>