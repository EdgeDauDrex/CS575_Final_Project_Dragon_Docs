<%@page import="edu.drexel.cs575.data.DB"%>
<%@page import="edu.drexel.cs575.model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>
</head>
<body>
<%
		String bio=request.getParameter("bio");
        String password=request.getParameter("password");
        String username=request.getParameter("username");
        DB db = new DB();
        User user = db.getUserByName(username);
        user = db.updateUser(user,password,bio);
        if(user != null){
        	response.sendRedirect("/DragonDocs/users/"+username+"/?uid="+username+"&auth="+Integer.toHexString(user.hashCode()));
        }else{
        	response.sendRedirect("/DragonDocs/user_fail");
        }
    %>
</body>
</html>