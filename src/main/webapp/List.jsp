<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
	import="kensyu.QuestionsBean"
	import="kensyu.QuestionsDao"
	import="kensyu.CorrectAnswersBean"
	import="kensyu.CorrectAnswersDao"
%>
    
    
 <%
	ArrayList<QuestionsBean> q_list = (ArrayList<QuestionsBean>)request.getAttribute("q_bean_list");
 	ArrayList<CorrectAnswersBean> a_list = (ArrayList<CorrectAnswersBean>)request.getAttribute("a_bean_list");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>List</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<button onclick="location.href='./Top'">top</button>
			<button>logout</button>
		</div>
		<button class="new_btn" onclick="location.href='./Register'" >新規登録</button>
		<% for (QuestionsBean que : q_list) { %>
			<div class="list_area">
				<div class="list">
					<div class="questions_list">
						<label>問題:<%= que.getId() %></label>
						<p><%= que.getQuestion() %></p>
					</div>
					<% int cnt = 0; %>
					<% for(CorrectAnswersBean ans : a_list) { %>
						<% if(ans.getQuestionsId() != que.getId()) { continue; } %>
						<div class="answers_list">
							<p>答え<%= ++cnt %>: <%= ans.getAnswer() %></p>
						</div>
					<% } %>
				</div>
				<div class="edit_delete_btn_area">
					<button>編集</button>
					<button>削除</button>
				</div>
			</div>
		<% } %>
	</body>
</html>