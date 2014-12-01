<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<c:if test="${user.error != null}">
<div><h3>Error : ${user.error}</h3></div>
</c:if>
<c:if test="${user.error == null}">
<div>
<h3>User : ${user.username}</h3>
<h4>Joined on : ${user.joinDate} and Updated on : ${user.updateDate}</h4>
<br>
${user.bio}
<br>
<a href="/DragonDocs/users/${user.username}/bio?uid=${name}&auth=${auth}">Edit Bio & Change Password</a>
<br>
<br>
<a href="/DragonDocs/users/${user.username}/contents?uid=${name}&auth=${auth}">${user.username}'s Content</a>
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