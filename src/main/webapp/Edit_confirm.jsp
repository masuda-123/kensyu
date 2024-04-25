<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%
	/*リクエストスコープから問題を取得*/
	String question = (String)request.getAttribute("question");
	/*リクエストスコープから答えを取得*/
	String[] answers = (String[])request.getAttribute("answers");
	/*リクエストスコープから問題idを取得*/
	String questionId = (String)request.getAttribute("questionId");
	/*リクエストスコープから答えidを取得*/
	String[] answersId = (String[])request.getAttribute("answersId");
	/*リクエストスコープからエラーメッセージを取得*/
	String errorMessage = (String)request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Confirm</title>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<!-- Top画面に遷移させるボタン  -->
			<button onclick="location.href='../Top'">top</button>
			<!-- Logout画面に遷移させるボタン  -->
			<button onclick="location.href='../Logout'">logout</button>
		</div>
		<!-- formタグで入力されたデータを./EditCompleteにpostで送信する  -->
		<form action="../EditComplete" method="post">
			<!-- エラーメッセージがある場合  -->
			<% if (!errorMessage.isEmpty()) { %>
				<!-- エラーメッセージを表示  -->
				<p class="error"><%= errorMessage %></p>
			<% } %>
			<!-- 問題idを表示  -->
			<div class="question_id_area">
				<label>問題番号:</label>
				<p><%= questionId %></p>
			</div>
			<!-- 隠し入力欄に、問題idを設定  -->
			<input type="hidden" id="questionId" name="questionId" value="<%= questionId %>">
			<div class="edit_question_area">
				<!-- "問題:"を表示  -->
				<label>問題:</label>
				<!-- 問題文を表示  -->
				<p><%= question %></p>
				<!-- 隠し入力欄に、問題文を設定  -->
				<input type="hidden" id="question" name="question" value="<%= question %>">
			</div>
			<div class="edit_answers_area">
				<!-- "答え:"を表示  -->
				<label>答え:</label>
				<div class="answers">
					<!-- 答えの数だけ繰り返す  -->
					<% for(String answer : answers){ %>
						<!-- 答えを表示  -->
						<p><%= answer %></p>
						<!-- 隠し入力欄に、答えを設定  -->
						<input type="hidden" id="answer" name="answer" value="<%= answer %>">
					<% } %>
					<!-- 答えidの数だけ繰り返す  -->
					<% for(String answerId : answersId){ %>
						<!-- 隠し入力欄に、答えidを設定  -->
						<input type="hidden" id="answerId" name="answerId" value="<%= answerId %>">
					<% } %>
				</div>
			</div>
			<div class="bottom_btn_area">
				<!-- 1つ前のページに戻るボタン  -->
				<button type="button" onclick="history.back()">戻る</button>
				<!-- エラーメッセージがない場合  -->
				<% if (errorMessage.isEmpty()) { %>
					<!-- フォームデータを送信するボタン  -->
					<input type="submit" value="更新">
				<% } %>
			</div>
		</form>
	</body>
</html>