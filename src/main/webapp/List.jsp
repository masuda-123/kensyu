<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
	import="kensyu.QuestionsBean"
	import="kensyu.CorrectAnswersBean"
%>
    
    
 <%
	ArrayList<QuestionsBean> queList = (ArrayList<QuestionsBean>)request.getAttribute("queList");
 	ArrayList<CorrectAnswersBean> ansList = (ArrayList<CorrectAnswersBean>)request.getAttribute("ansList");
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
		<% for (QuestionsBean que : queList) { %>
			<div class="list_area">
				<div class="list">
					<div class="questions_list">
						<label>問題:<%= que.getId() %></label>
						<p><%= que.getQuestion() %></p>
					</div>
					<% int cnt = 0; %>
					<% for(CorrectAnswersBean ans : ansList) { %>
						<% if(ans.getQuestionsId() != que.getId()) { continue; } %>
						<div class="answers_list">
							<p>答え<%= ++cnt %>: <%= ans.getAnswer() %></p>
						</div>
					<% } %>
				</div>
				<div class="edit_delete_btn_area">
					<a href="./Edit?id=<%= que.getId() %>"><button>編集</button></a>
					<a href="./Delete_confirm?id=<%= que.getId() %>"><button>削除</button></a>
				</div>
			</div>
		<% } %>
	</body>
</html>