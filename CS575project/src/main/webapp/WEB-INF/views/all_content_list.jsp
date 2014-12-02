<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="include.jsp" %>
    <%@ page import="edu.drexel.cs575.model.Projects"%>
    <%@ page import="edu.drexel.cs575.model.Project" %>
    <%@page import="java.util.List" %>
    <%@page import="edu.drexel.cs575.model.User" %>
    <%@ page import="edu.drexel.cs575.data.DB" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Contents</title>
</head>
<body>
<h1>Dragon Docs</h1>
<h2>You are currently here as : ${name}</h2>
<c:if test="${contents.error != null}">
<h3>Error : ${contents.error}</h3>
</c:if>
<c:if test="${contents.error == null}">
<%
	DB db = new DB();
	Projects proj = (Projects)request.getAttribute("contents");
	List<Project> plist = proj.getProjects();
	String fullId, uid, conid, catid, pid, pname, summary;
	User u;
	String auth = request.getParameter("auth");
	String name = (String)request.getAttribute("name");
	String link;
	for(Project p : plist){
		fullId = p.getFullId().substring(1);
		uid = fullId.split("CON")[0];
		u = db.getUserByID(Integer.parseInt(uid));
		conid = fullId.split("CON")[1].split("CAT")[0];
		catid = fullId.split("CON")[1].split("CAT")[1].split("PROJ")[0];
		pid = fullId.split("PROJ")[1];
		pname = p.getName();
		summary = p.getSummary();
		link = "<a href=\"/DragonDocs/users/"+u.getUsername()+"/contents/"+conid+"/categories/"+catid+"/projects/"+pid+"?uid="+name+"&auth="+auth+"\">"+pname+"</a><br>"+summary+"<br><br>";
		out.println(link);
	}
%>
</c:if>
<br>
<br>
<a href="/DragonDocs/start?uid=${name}&auth=${auth}">Return Home</a>
</body>
</html>