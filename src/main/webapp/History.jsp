<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
    import="kensyu.HistoriesBean"
    import="kensyu.UsersBean"
    import="java.text.SimpleDateFormat"
%>

<%
	ArrayList<HistoriesBean> hisList = (ArrayList<HistoriesBean>)request.getAttribute("hisList");
	UsersBean user = (UsersBean)session.getAttribute("user");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>History</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
	<div class="btn_area">
		<button onclick="location.href='./Top'">top</button>
		<button>logout</button>
	</div>
	<h2>履歴</h2>
	<table>
		 <tr>
		 	<th>氏名</th>
		 	<th>得点</th>
		 	<th>採点時間</th>
		 </tr>
		 <% for(HistoriesBean his : hisList) { %>
			 <tr>
			 	<td><%= user.getName() %></td>
			 	<td><%= his.getPoint() %></td>
			 	<td><%= sdf.format(his.getCreatedAt()) %></td>
			 </tr>
		<% } %>
	</table>
</body>
</html>