<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
    
<%
	String question = (String)request.getAttribute("reg_question");
	String[] answers = (String[])request.getAttribute("reg_answers");
	String errorMessage = (String)request.getAttribute("errorMessage");
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
	    <% if (!errorMessage.isEmpty()) { %>
            <p class="error"><%= errorMessage %></p>
        <% } %>
		<div class="question_area">
			<label>問題:</label>
			<p><%= question %></p>
			<input type="hidden" id="question" name="question" value="<%= question %>">
		</div>
		<div class="answer_area">
			<label>答え:</label>
			<div class="answer_form_list">
				<% for(String answer : answers){  %>
					<p><%= answer %></p>
					<input type="hidden" id="answer" name="answer" value="<%= answer %>">
				<% } %>
			</div>
		</div>
		<div class="btn_area">
			<button type="button" onclick="history.back()">戻る</button>
			<% if (errorMessage.isEmpty()) { %>
				<input type="submit" value="登録">
			<% } %>
		</div>
	</form>
</body>
</html>