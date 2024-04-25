<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
%>

<%
	/*リクエストスコープから問題を取得*/
	String question = (String)request.getAttribute("question");
	/*リクエストスコープから問題idを取得*/
	int questionId = (int)request.getAttribute("questionId");
	/*リクエストスコープから答えを取得*/
	String[] answers = (String[])request.getAttribute("answers");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Delete_confirm</title>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<!-- logoutやtopボタンを読み込む  -->
		<%@ include file="Header.jsp"%>
		<!-- formタグで入力されたデータを./DeleteCompleteにpostで送信  -->
		<form action="./DeleteComplete" method="post">
			<div class="question_area">
				<!-- "問題:"を表示  -->
				<label>問題:</label>
				<!-- 問題文を表示  -->
				<p><%= question %></p>
				<!-- 隠し入力欄に、問題文を設定  -->
				<input type="hidden" id="questionId" name="questionId" value=<%= questionId %>>
			</div>
			<div class="answers_area">
				<!-- "答え:"を表示  -->
				<label>答え:</label>
				<div class="answers">
					<!-- 答えの数だけ繰り返す  -->
					<% for(String answer : answers){  %>
						<!-- 答えを表示  -->
						<p><%= answer %></p>
					<% } %>
				</div>
			</div>
			<div class="bottom_btn_area">
				<!-- 1つ前のページに戻るボタン  -->
				<button type="button" onclick="history.back()">戻る</button>
				<!-- フォームデータを送信するボタン  -->
				<input type="submit" value="削除">
			</div>
		</form>
	</body>
</html>