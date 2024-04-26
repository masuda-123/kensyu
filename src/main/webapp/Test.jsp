<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
	import="kensyu.QuestionsBean"
	import="kensyu.QuestionsDao"
	import="java.util.Collections"
%>
    
<%
	/*リクエストスコープから登録してある問題を取得*/
	ArrayList<QuestionsBean> queList = (ArrayList<QuestionsBean>)request.getAttribute("queList");
	/* 問題の順番をランダムに入れ替える  */
	Collections.shuffle(queList);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Test</title>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<!-- logoutやtopボタンを読み込む  -->
		<%@ include file="Header.jsp"%>
		<!-- formタグで入力されたデータを./Test/Resultにpostで送信する  -->
		<form action ="./Test/Result" method="post">
			<% int queNum = 0; %>
			<!-- 問題の数だけ処理を繰り返す  -->
			<% for (QuestionsBean que : queList) { %>
				<div class="test_question_area">
					<!-- 問題番号を表示  -->
					<label><%= ++queNum %></label>
					<!-- 問題文表示  -->
					<p><%= que.getQuestion() %></p>
					<!-- 隠し入力欄に、問題idを設定  -->
					<input type="hidden" id="questions_id" name="questions_id" value="<%= que.getId() %>">
				</div>
				<div class="test_answers_area">
					<label for="answer">回答</label>
					<div class="test_answer_form">
						<!-- 答えを入力する欄 -->
						<input type="text" id="answer" name="answer">
					</div>
				</div>
			<% } %>
			<div class="bottom_btn_area">
				<!-- フォームデータを送信するボタン  -->
				<input type="submit" value="採点">
			</div>
		</form>
	</body>
</html>