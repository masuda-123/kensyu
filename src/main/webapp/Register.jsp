<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<script type="text/javascript">
			 var i = 1;
		</script>
		<script src="<%= request.getContextPath() %>/js/add_answer_form.js"></script>
		<script src="<%= request.getContextPath() %>/js/delete_answer_form.js"></script>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	
	<body>
		<%@ include file="Header.jsp"%>
		<form action="./Register/Confirm" method="post">
			<div class="question_area">
				<label for="question">問題:</label>
				<div class="question_form">
					<textarea id="question" name="question"></textarea>
				</div>
			</div>
			<div class="answer_area">
				<label for="answer">答え:</label>
				<div class="answer_form_list">
					<div class="answer_form_area" id="answerform1">
						<input type="text" id="answer" name="answer">
					</div>
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