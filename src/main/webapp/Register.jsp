<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
		<!-- jsファイルで使う変数を宣言し、初期値を設定   -->
		<script type="text/javascript">
			 var i = 1;
		</script>
		<!-- 追加ボタンを押した際の処理を記載したjsファイルを読み込む  -->
		<script src="<%= request.getContextPath() %>/js/add_answer_form.js"></script>
		<!-- 削除ボタンを押した際の処理を記載したjsファイルを読み込む  -->
		<script src="<%= request.getContextPath() %>/js/delete_answer_form.js"></script>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	
	<body>
		<!-- logoutやtopボタンを読み込む  -->
		<%@ include file="Header.jsp"%>
		<!-- formタグで入力されたデータを./Register/Confirmにpostで送信する  -->
		<form action="./Register/Confirm" method="post">
			<div class="question_form_area">
				<!-- 入力欄に対して"問題:"を表示  -->
				<label for="question">問題:</label>
				<!-- 問題文を入力する欄  -->
				<textarea id="question" name="question"></textarea>
			</div>
			<div class="answer_forms_area">
				<!-- 入力欄に対して"答え:"を表示  -->
				<label for="answer">答え:</label>
				<div class="answer_forms">
					<div class="answer_form" id="answerform1">
						<!-- 答えを入力する欄  -->
						<input type="text" id="answer" name="answer">
					</div>
				</div>
			</div>
			<div class="bottom_btn_area">
				<!-- ボタンを押下後、1つ前のページに戻る  -->
				<button type="button" onclick="history.back()">戻る</button>
				<!-- フォームデータを送信するボタン -->
				<input type="submit" value="確認">
				<!-- ボタンを押下後、jsファイルのaddForm関数を実行  -->
				<button type="button" onclick="addForm()">追加</button>
			</div>
		</form>
	</body>
</html>