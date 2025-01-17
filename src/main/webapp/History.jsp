<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="kensyu.HistoriesBean"
    import="kensyu.UsersBean"
    import="java.text.SimpleDateFormat"
%>

<%
	/* リクエストスコープから値を取得  */
	ArrayList<HistoriesBean> hisList = (ArrayList<HistoriesBean>)request.getAttribute("hisList");
	String userName = (String)request.getAttribute("userName");
	/* SimpleDateFormatオブジェクトを作成し、フォーマットを設定  */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>History</title>
		<!-- cssを読み込む  -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
		<!-- logoutやtopボタンを読み込む  -->
		<%@ include file="./common/Header.jsp"%>
		<h2>履歴</h2>
		<!-- 履歴が登録されていない場合  -->
		<% if(hisList.isEmpty()) {  %>
			<p>履歴が登録されていません</p>
		<!-- 履歴が登録されている場合  -->
		<% } else { %>
			<table>
				 <tr>
				 	<th>氏名</th>
				 	<th>得点</th>
				 	<th>採点時間</th>
				 </tr>
				 <!-- 履歴の数だけ繰り返す  -->
				 <% for(HistoriesBean his : hisList) { %>
					 <tr>
					 	<!-- ユーザー名を表示  -->
					 	<td><%= userName %></td>
					 	<!-- 点数を表示  -->
					 	<td><%= his.getPoint() %>点</td>
					 	<!-- 採点日時を yyyy/MM/dd HH:mm;ss の形式で表示  -->
					 	<td><%= sdf.format(his.getCreatedAt()) %></td>
					 </tr>
				<% } %>
			</table>
		<% } %>
	</body>
</html>