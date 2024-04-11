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
		<title>Test</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<button onclick="location.href='./Top'">top</button>
			<button>logout</button>
		</div>
		<form action ="./Register/Confirm" method="post">
		<% int i = 0; %>
		<% for (QuestionsBean que : q_list) { %>
			<div class="question_area">
		    	<label for="question"><%= ++i %></label>
		    		<div class="question_form">
		    			<%= que.getQuestion() %>
		    		</div>
				</div>
				<div class="answer_area">
					<label for="answer">回答:</label>
					<div class="answer_form_list">
						<div class="answer_form">
			    			<input type="text" id="answer" name="answer">
			    		</div>
		    		</div>
				</div>
			 </div>
		<% } %>
		<div class="btn_area">
			<input type="submit" value="採点">
		</div>
		</form>
	</body>
</html>