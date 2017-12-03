<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" 
	import="com.coder.blog.model.Cmt"
	import="com.coder.blog.model.Article"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//out.println(request.getAttribute("article").toString()); 
	Article ac = (Article)request.getAttribute("article");
	out.println("ac.getAbs():" + ac.getAbs());
	out.println("ac.getCont():" + ac.getCont());
	out.println("ac.getLike():" + ac.getLike());
	out.println("ac.getReading():" + ac.getReading());
	out.println("ac.getTitle():" + ac.getTitle());
	out.println("ac.getTopic():" + ac.getTopic());
	out.println("ac.getUser_id():" + ac.getUser_id());

	for (Cmt cmt : ac.getCmtlist()) {
		out.println("cmt.getColnum():" + cmt.getColnum());
		out.println("cmt.getCont():" + cmt.getCont());
		out.println("cmt.getUser():" + cmt.getUser());
	}
	for (Cmt cmt : ac.getRepcmtlist()) {
		out.println("repcmt.getColnum():" + cmt.getColnum());
		out.println("repcmt.getRepnum():" + cmt.getRepnum());
		out.println("repcmt.getCont():" + cmt.getCont());
		out.println("repcmt.getUser():" + cmt.getUser());
	}
%>
	<h2>Success Page</h2>
</body>
</html>