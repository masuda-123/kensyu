<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="kensyu.UsersBean"
	import="java.text.SimpleDateFormat"
	import="java.util.Date"
%>
    
<%
	int correctQueCnt = (int)request.getAttribute("correctQueCnt");
	int queCnt = (int)request.getAttribute("queCnt");
	int point = (int)request.getAttribute("point");
	UsersBean user = (UsersBean)session.getAttribute("user");
	Date dateTime = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Result</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="btn_area">
		<button onclick="location.href='../Top'">top</button>
		<button>logout</button>
	</div>
	<h2>テスト結果</h2>
	<p><%= user.getName() %>さん</p>
	<p><%= queCnt %>問中<%= correctQueCnt %>問正解です。</p>
	<p><%= point %>点でした。</p>
	
	<div class="buttom_menu_area">
		<p><%= sdf.format(dateTime) %></p>
		<a href='../History'">採点結果履歴へ</a>
	</div>
</body>
</html>