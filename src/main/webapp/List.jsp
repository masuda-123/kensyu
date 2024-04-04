<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
	import="kensyu.QuestionsBean"
	import="kensyu.QuestionsDao"
%>
    
    
 <%
	ArrayList<QuestionsBean> q_list = (ArrayList<QuestionsBean>)request.getAttribute("q_bean_list");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>問題一覧画面</title>
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
				<!-- getメソッドを使ってリストから関数を取得 -->
				<% QuestionsBean que = q_list.get(i); %>
				<li>
					問題:<%= que.getId() %>
					<%= que.getQuestion() %>
				</li>
			<% } %>
		</ul>
	</body>
</html>