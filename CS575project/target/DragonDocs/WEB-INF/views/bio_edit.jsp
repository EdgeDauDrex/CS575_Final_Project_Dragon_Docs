<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bio Edit</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<c:if test="${user.error != null}">
<div><h3>Error : ${user.error}</h3></div>
</c:if>
<c:if test="${user.error == null && user.username != name || user.id == 0}">
<div>
<h3>You are not authorized to edit this bio.</h3>
</div>
</c:if>
<c:if test="${user.error == null && user.username == name && user.id != 0}">
<div>
<h3>User : ${user.username}</h3>
<h4>Joined on : ${user.joinDate} and Updated on : ${user.updateDate}</h4>
<br>
<form action="/DragonDocs/updateUser.jsp" method="post">
		 <br/>Username:<input type="text" name="username" readonly="readonly" value="${user.username}">
		 <br>
         <br/>Password:<input type="password" name="password" value="${user.password}">
         <br>
         <br/>Bio:<textarea name="bio" rows="10" cols="200" wrap="hard">${user.bio}</textarea>
         <br>
         <br/><input type="submit" value="Submit">
         <br>
    </form>
</div>
</c:if>
<a href="/DragonDocs/users/${user.username}/?uid=${name}&auth=${auth}">Back</a>
<br>
<br>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">Your Profile</a>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>