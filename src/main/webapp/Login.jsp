<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<form action="./Login" method="post">
			<div class="form">
				<label for="userId">ID:</label>
				<input type="number" id="userId" name="userId">
			</div>
			<div class="form">
			    <label for="password">pw:</label>
			    <input type="password" id="password" name="password">
			</div>
			<div class="form">
		    	<input type="submit" value="ログイン" class="login_btn">
		    </div>
		</form>
	</body>
</html>