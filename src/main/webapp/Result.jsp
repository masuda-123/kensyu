<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="kensyu.UsersBean"
	import="java.text.SimpleDateFormat"
	import="java.util.Date"
%>
    
<%
	/*リクエストスコープから正解の問題数を取得*/
	int correctQueCnt = (int)request.getAttribute("correctQueCnt");
	/*リクエストスコープから問題数を取得*/
	int queCnt = (int)request.getAttribute("queCnt");
	/*リクエストスコープから点数を取得*/
	int point = (int)request.getAttribute("point");
	/*リクエストスコープからユーザー名を取得*/
	String userName = (String)request.getAttribute("userName");
	/* Dateオブジェクトを作成  */
	Date dateTime = new Date();
	/* SimpleDateFormatオブジェクトを作成し、フォーマットを設定  */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Result</title>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<!-- Top画面に遷移させるボタン  -->
			<button onclick="location.href='../Top'">top</button>
			<!-- Logout画面に遷移させるボタン  -->
			<button onclick="location.href='../Logout'">logout</button>
		</div>
		<h2>テスト結果</h2>
		<!-- ユーザー名を表示  -->
		<p><%= userName %>さん</p>
		<!-- 全体の問題数と正解数を表示  -->
		<p><%= queCnt %>問中<%= correctQueCnt %>問正解です。</p>
		<!-- 点数を表示  -->
		<p><%= point %>点でした。</p>
		
		<div class="buttom_menu_area">
			<!-- 現在日時を yyyy/MM/dd HH:mm:ss の形式で表示  -->
			<p><%= sdf.format(dateTime) %></p>
			<!-- History画面に遷移するボタン  -->
			<a href='../History'">採点結果履歴へ</a>
		</div>
	</body>
</html>