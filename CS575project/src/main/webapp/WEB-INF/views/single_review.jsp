<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single Review</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<c:if test="${user.error != null}">
<h3>Error : ${user.error}</h3>
</c:if>
<c:if test="${content.error != null}">
<h3>Error : ${content.error}</h3>
</c:if>
<c:if test="${category.error != null}">
<h3>Error : ${category.error}</h3>
</c:if>
<c:if test="${project.error != null}">
<h3>Error : ${project.error}</h3>
</c:if>
<c:if test="${page.error != null}">
<h3>Error : ${page.error}</h3>
</c:if>
<c:if test="${review.error != null}">
<h3>Error : ${review.error}</h3>
</c:if>
<c:if test="${user.error == null && content.error == null && category.error == null && project.error == null && page.error == null && review.error == null}">
<h3>${category.name}</h3>
<h4>${project.name}</h4>
<h5>${page.name}</h5>
<h6>${review.uname}</h6>
<br>
<br>
${review.reviewDate}
<br>
<br>
${review.review}
</c:if>
<br>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages/${page.id}/reviews?uid=${name}&auth=${auth}">Back</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages/${page.id}?uid=${name}&auth=${auth}">${page.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}/pages?uid=${name}&auth=${auth}">List of Pages</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects/${project.id}?uid=${name}&auth=${auth}">${project.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}/projects?uid=${name}&auth=${auth}">Projects Page</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories/${category.id}?uid=${name}&auth=${auth}">${category.name}</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}/categories?uid=${name}&auth=${auth}">Return to Categories Page</a>
<br>
<a href="/DragonDocs/users/${user.username}/contents/${content.id}?uid=${name}&auth=${auth}">Return to ${content.name}</a>
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