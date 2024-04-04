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
		<title>問題一覧</title>
	</head>
	<body>
		<ul>
			<li>
				<button  onclick="location.href='./Top'">top</button>
				<button>logout</button>
			</li>
			<li>
				<button>新規作成</button>
			</li>
			<% for (int i = 0; i < q_list.size(); i++ ){ %>
				<% QuestionsBean que = q_list.get(i); %>
				<li>
					問題:<%= que.getId() %>
					<%= que.getQuestion() %>
				</li>
				<% int cnt = 0; %>
				<% for (int j = 0; j < a_list.size(); j++ ) { %>
					<% CorrectAnswersBean ans = a_list.get(j); %>
					<% if(ans.getQuestionsId() == que.getId()){ %>
						<li>
							答え:<%= ++cnt %> <%= ans.getAnswer() %>
						</li>
					<% } %>
				<% } %>
			<% } %>
		</ul>
	</body>
</html>