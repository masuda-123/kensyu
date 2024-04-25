<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="kensyu.CorrectAnswersBean"
%>

<%
	/*リクエストスコープから問題を取得*/
	String question = (String)request.getAttribute("question");
	/*リクエストスコープから問題idを取得*/
	int questionId = (int)request.getAttribute("questionId");
	/*リクエストスコープから問題に紐づく答えを取得*/
	ArrayList<CorrectAnswersBean> answers = (ArrayList<CorrectAnswersBean>)request.getAttribute("answers");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit</title>
		<!-- jsファイルで使う変数を宣言し、初期値として登録されている答えの数を設定  -->
		<script type="text/javascript">
			 var i = <%= answers.size() %>;
		</script>
		<!-- 追加ボタンを押した際の処理を記載したjsファイルを読み込む  -->
		<script src="<%= request.getContextPath() %>/js/add_answer_form.js"></script>
		<!-- 削除ボタンを押した際の処理を記載したjsファイルを読み込む  -->
		<script src="<%= request.getContextPath() %>/js/delete_answer_form.js"></script>
		<!-- cssを読み込む -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<!-- logoutやtopボタンを読み込む  -->
		<%@ include file="Header.jsp"%>
		<!-- formタグで入力されたデータを./Edit/Confirmにpostで送信する  -->
		<form action="./Edit/Confirm" method="post">
			<!-- 問題のidを表示  -->
			<div class="question_id_area">
				<label>問題番号:</label>
				<p><%= questionId %></p>
			</div>
			<!-- 隠し入力欄に、問題idを設定  -->
			<input type="hidden" id="questionId" name="questionId" value="<%= questionId %>">
			<div class="question_form_area">
				<!-- 入力欄に対して"問題:"を表示  -->
				<label for="question">問題:</label>
				<!-- 問題文を入力する欄（初期値として問題文を設定）  -->
				<textarea id="question" name="question"><%= question %></textarea>
			</div>
			
			<div class="answer_forms_area">
				<!-- 入力欄に対して"答え:"を表示  -->
				<label for="answer">答え:</label>
				<div class="answer_forms">
					<!-- 答えの数だけ処理を繰り返す  -->
					<% for(int i = 0; i < answers.size(); i++){  %>
						<div class="answer_form" id="answer_form<%= i + 1 %>">
							<!-- 答えの文を表示  -->
							<input type="text" id="answer" name="answer" value="<%= answers.get(i).getAnswer() %>">
							<!-- 隠し入力欄に、答えのidを設定  -->
							<input type="hidden" id="answerId" name="answerId" value="<%= answers.get(i).getId() %>">
							<!-- 1つ目の答え以外の場合  -->
							<% if(i != 0) { %>
								<!-- 削除ボタンを表示し、ボタンを押下後、jsファイルのdeleteForm関数を実行（引数としてタグのidを渡す）  -->
								<button type="button" onclick="deleteForm(answer_form<%= i + 1 %>)">削除</button>
							<% } %>
						</div>
					<% } %>
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