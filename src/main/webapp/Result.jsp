<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int correctAnswersCount = (int)request.getAttribute("correctAnswersCount");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Result</title>
	</head>
<body>
	<%= correctAnswersCount %>
</body>
</html>