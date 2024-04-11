<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
    
<%
	String question = (String)request.getAttribute("reg_question");
	String[] answers = (String[])request.getAttribute("reg_answers");
	String error_empty_question = (String)request.getAttribute("error_empty_question");
	String error_empty_answer = (String)request.getAttribute("error_empty_answer");
	String error_length_question = (String)request.getAttribute("error_length_question");
	String error_length_answer = (String)request.getAttribute("error_length_answer");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Confirm</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="btn_area">
		<button onclick="location.href='../Top'">top</button>
		<button>logout</button>
	</div>
	<form action="./RegisterComplete" method="post">
		<% if(error_empty_question != null) { %>
			<p class="error"><%= error_empty_question %></p>
		<% } else if(error_length_question != null) { %>
			<p class="error"><%= error_length_question %></p>
		<% } %>
		<div class="question_area">
			<label>問題:</label>
			<p><%= question %></p>
			<input type="hidden" id="question" name="question" value="<%= question %>">
		</div>
		
		<% if(error_empty_answer != null) { %>
			<p class="error"><%= error_empty_answer %></p>
		<% } else if(error_length_answer != null) { %>
			<p class="error"><%= error_length_answer %></p>
		<% } %>
		<div class="answer_area">
			<label>答え:</label>
			<div class="answer_form_list">
				<% for(int i = 0; i < answers.length; i++){  %>
					<p><%= answers[i] %></p>
					<input type="hidden" id="answer" name="answer" value="<%= answers[i] %>">
				<% } %>
			</div>
		</div>
		<div class="btn_area">
			<button type="button" onclick="history.back()">戻る</button>
			<% if (error_empty_question == null && error_empty_answer == null && error_length_question == null && error_length_answer == null) { %>
				<input type="submit" value="登録">
			<% } %>
		</div>
	</form>
</body>
</html>