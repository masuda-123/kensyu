<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
    
<%
	String question = String.valueOf(request.getAttribute("reg_question"));
	String[] answers = (String[])request.getAttribute("reg_answers");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Confirm</title>
</head>
<body>
	<ul>
		<li>
			<button onclick="location.href='./Top'">top</button>
			<button>logout</button>
		</li>
		<li>
			<label>問題:</label>
			<%= question %>
		</li>
		<li>
			<label>答え:</label>
			<% for(int i = 0; i < answers.length; i++){  %>
						<%= answers[i] %>
			<% } %>
		</li>
		<form action="./Confirm" method="post">
			<button type="button" onclick="history.back()">戻る</button>
			<input type="submit" value="登録">
		</form>
	</ul>
</body>
</html>