<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
    
<%
	String question = (String)request.getAttribute("reg_question");
	String[] answers = (String[])request.getAttribute("reg_answers");
	String error_empty_question = (String)request.getAttribute("error_empty_question");
	String error_length_question = (String)request.getAttribute("error_length_question");
	String error_length_answer = (String)request.getAttribute("error_length_answer");
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
			<button onclick="location.href='../Top'">top</button>
			<button>logout</button>
		</li>
		<% if(error_empty_question != null) { %>
			<li>
		    	<%= error_empty_question %>
		    </li>
		<% } else if(error_length_question != null) { %>
		    <li>
		    	<%= error_length_question %>
		    </li>
		<% } %>
		<li>
			<label>問題:</label>
			<%= question %>
		</li>
		<% if(error_length_answer != null) { %>
		    <li>
		    	<%= error_length_answer %>
		    </li>
		<% } %>
		<li>
			<label>答え:</label>
			<% for(int i = 0; i < answers.length; i++){  %>
				<%= answers[i] %>
			<% } %>
		</li>
		<form action="./RegisterComplete" method="post">
			<button type="button" onclick="history.back()">戻る</button>
			<% if (error_empty_question == null && error_length_question == null && error_length_answer == null) { %>
				<input type="submit" value="登録">
			<% } %>
		</form>
	</ul> 
</body>
</html>