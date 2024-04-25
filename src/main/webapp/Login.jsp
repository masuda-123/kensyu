<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<!-- cssを読み込む -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<!-- formタグで入力されたデータを./Loginにpostで送信する  -->
		<form action="./Login" method="post">
			<div class="form">
				<!-- 入力欄に対して"ID:"を表示  -->
				<label for="userId">ID:</label>
				<!-- userIdを入力する欄  -->
				<input type="number" id="userId" name="userId">
			</div>
			<div class="form">
				<!-- 入力欄に対して"ID:"を表示  -->
				<label for="password">pw:</label>
				<!-- パスワードを入力する欄  -->
				<input type="password" id="password" name="password">
			</div>
			<div class="form">
				<!-- フォームデータを送信するボタン -->
		    	<input type="submit" value="login" class="login_btn">
		    </div>
		</form>
	</body>
</html>