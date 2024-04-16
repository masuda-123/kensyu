<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Top</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="btn_area">
		<button>logout</button>
	</div>
	<div class="menu_area">
		<button onclick="location.href='./List'">問題と答えを確認・登録する ></button>
		<button onclick="location.href='./Test'">テストをする ></button>
		<button>過去の採点結果をみる ></button>
	</div>
</body>
</html>