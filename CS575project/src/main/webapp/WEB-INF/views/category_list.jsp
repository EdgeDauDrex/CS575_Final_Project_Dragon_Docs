<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category Listing</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<c:if test="${user.error == null}">
<h3>Category List For User : ${user.username}</h3>
</c:if>
<c:if test="${user.error != null}">
<h3>Category List With User Error : ${user.error}</h3>
</c:if>
<c:if test="${content.error != null}">
<div>Content Error : ${content.error}</div>
</c:if>
<c:if test="${content.error == null}">
<div><h4>${content.name}</h4></div>
</c:if>
<c:if test="${categories.error != null}">
<div>Error : ${categories.error}</div>
</c:if>
<c:if test="${categories.error == null}">
<div>
<table>
<c:forEach items="${categories.categories}" var="category">
	<tr>
		<td><a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}?uid=${name}&auth=${auth}">${category.name}</a></td>
	</tr>
</c:forEach>
</table>
</div>
</c:if>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}?uid=${name}&auth=${auth}">Back</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents?uid=${name}&auth=${auth}">Return to Contents Page</a>
<br>
<a href="/DragonDocs/users/${user.username}?uid=${name}&auth=${auth}">Return to ${user.username}'s Profile</a>
<br>
<br>
<a href="/DragonDocs/users/${name}?uid=${name}&auth=${auth}">Your Profile</a>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>