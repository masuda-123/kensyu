<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Top</title>
	<!-- cssを読み込む  -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="btn_area">
		<!--  logoutボタンを押下後、Logout.java の doGetメソッドの処理に移動 -->
		<button onclick="location.href='./Logout'">logout</button>
	</div>
	<div class="menu_area">
		<!-- ボタンを押下後、List画面に遷移させる  -->
		<button onclick="location.href='./List'">問題と答えを確認・登録する ></button>
		<!-- ボタンを押下後、Test画面に遷移させる  -->
		<button onclick="location.href='./Test'">テストをする ></button>
		<!-- ボタンを押下後、History画面に遷移させる  -->
		<button onclick="location.href='./History'">過去の採点結果をみる ></button>
	</div>
</body>
</html>