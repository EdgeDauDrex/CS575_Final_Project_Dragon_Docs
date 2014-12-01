<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<%@ page import="edu.drexel.cs575.model.Users" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dragon Docs User List</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<h3>User List</h3>

<c:if test="${users.error != null}">
<div><h4>${users.error}</h4></div>
</c:if>
<c:if test="${users.error == null}">
<div>
<table>
<c:forEach items="${users.users}" var="user">
	<tr>
		<td><a href="/DragonDocs/users/${user.username}?uid=${name}&auth=${auth}">${user.username}</a></td>
	</tr>
</c:forEach>
</table>
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