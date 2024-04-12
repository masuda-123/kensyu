<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int correctQueCnt = (int)request.getAttribute("correctQueCnt");
	int queCnt = (int)request.getAttribute("queCnt");
	int score = (int)request.getAttribute("score");
	String userName = (String)session.getAttribute("userName");
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
		<button onclick="location.href='./Top'">top</button>
		<button>logout</button>
	</div>
	<h2>テスト結果</h2>
	<p><%= userName %>さん</p>
	<p><%= queCnt %>問中<%= correctQueCnt %>問正解です。</p>
	<p><%= score %>点でした。</p>
</body>
</html>