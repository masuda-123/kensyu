<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="kensyu.CorrectAnswersBean"
%>

<%
	String question = (String)request.getAttribute("question");
	int questionId = (int)request.getAttribute("questionId");
	ArrayList<CorrectAnswersBean> answers = (ArrayList<CorrectAnswersBean>)request.getAttribute("answers");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit</title>
		<script type="text/javascript">
			 var i = <%= answers.size() %>;
		</script>
		<script src="<%= request.getContextPath() %>/js/add_answer_form.js"></script>
		<script src="<%= request.getContextPath() %>/js/delete_answer_form.js"></script>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		 <div class="btn_area">
			<button onclick="location.href='./Top'">top</button>
			<button onclick="location.href='./Logout'">logout</button>
		</div>
		<form action="./Edit/Confirm" method="post">
			<p>問題番号:<%= questionId %></p>
			<input type="hidden" id="questionId" name="questionId" value="<%= questionId %>">
			<div class="question_area">
				<label for="question">問題:</label>
				<div class="question_form">
					<textarea id="question" name="question"><%= question %></textarea>
				</div>
			</div>
			
			<div class="answer_area">
				<label for="answer">答え:</label>
				<div class="answer_form_list">
					<% for(int i = 0; i < answers.size(); i++){  %>
						<div class="answer_form" id="answer_form<%= i + 1 %>">
							<input type="text" id="answer" name="answer" value="<%= answers.get(i).getAnswer() %>">
							<input type="hidden" id="answerId" name="answerId" value="<%= answers.get(i).getId() %>">
							<% if(i != 0) { %>
								<button type="button" onclick="deleteForm(answer_form<%= i + 1 %>)">削除</button>
							<% } %>
						</div>
					<% } %>
				</div>
			</div>
			<div class="btn_area">
				<button type="button" onclick="history.back()">戻る</button>
				<input type="submit" value="確認">
				<button type="button" onclick="addForm()">追加</button>
			</div>
		</form>
	</body>
</html>