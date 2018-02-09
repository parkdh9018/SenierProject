<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mongo.board.*" %>
<%@ page session="false" %>
<html>
<head>
	<title>list</title>
</head>
<body>
<%
	List list = (List)request.getAttribute("list");
	out.println("개수 : "+list.size()+"<br>");
	for(int i=0;i<list.size();i++)
		out.println(list.get(i).toString());
%>
</body>
</html>
