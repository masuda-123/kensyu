<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
	import="kensyu.QuestionsBean"
	import="kensyu.CorrectAnswersBean"
%>
    
    
 <%
 	/*リクエストスコープから登録してある問題を取得*/
 	ArrayList<QuestionsBean> queList = (ArrayList<QuestionsBean>)request.getAttribute("queList");
 	/*リクエストスコープから登録してある答えを取得*/
 	ArrayList<CorrectAnswersBean> ansList = (ArrayList<CorrectAnswersBean>)request.getAttribute("ansList");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>List</title>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<!-- logoutやtopボタンを読み込む  -->
		<%@ include file="Header.jsp"%>
		<!-- ボタンを押下後、Register画面に遷移させる  -->
		<button class="new_btn" onclick="location.href='./Register'" >新規登録</button>
		<!-- 問題の数だけ処理を繰り返す  -->
		<% for (QuestionsBean que : queList) { %>
			<div class="list_area">
				<div class="list">
					<div class="questions_list">
						<!-- 問題のidを表示  -->
						<label>問題:<%= que.getId() %></label>
						<!-- 問題文を表示  -->
						<p><%= que.getQuestion() %></p>
					</div>
					<!-- 答えの番号を格納する変数を宣言  -->
					<% int cnt = 0; %>
					<!-- 答えの数だけ処理を繰り返す  -->
					<% for(CorrectAnswersBean ans : ansList) { %>
						<!-- 答えのquestions_idと問題のidが一致しない場合は、繰り返しの先頭に戻る  -->
						<% if(ans.getQuestionsId() != que.getId()) { continue; } %>
						<div class="answers_list">
							<!-- 答えの番号をカウントアップし、答えの文を表示  -->
							<p>答え<%= ++cnt %>: <%= ans.getAnswer() %></p>
						</div>
					<% } %>
				</div>
				<div>
					<!-- ボタンを押下後、Edit画面に遷移させる（パラメータに問題のidを設定）  -->
					<a href="./Edit?id=<%= que.getId() %>"><button>編集</button></a>
					<!-- ボタンを押下後、Delete_confirm画面に遷移させる（パラメータに問題のidを設定  -->
					<a href="./Delete_confirm?id=<%= que.getId() %>"><button>削除</button></a>
				</div>
			</div>
		<% } %>
	</body>
</html>