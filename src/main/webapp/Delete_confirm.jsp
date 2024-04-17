<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String question = (String)request.getAttribute("question");
/* 	String[] answers = (String[])request.getAttribute("answers"); */
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Delete_confirm</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="btn_area">
		<button onclick="location.href='./Top'">top</button>
		<button>logout</button>
	</div>
		<div class="question_area">
			<label>問題:</label>
			<p><%= question %></p>
		</div>
		<div class="answer_area">
			<label>答え:</label>
<%-- 			<div class="answer_form_list">
				<% for(String answer : answers){  %>
					<p><%= answer %></p>
				<% } %>
			</div> --%>
		</div>
		<div class="btn_area">
			<button type="button" onclick="history.back()">戻る</button>
			<input type="submit" value="削除">
		</div>
	</form>

</body>
</html>