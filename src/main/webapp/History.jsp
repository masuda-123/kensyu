<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	 <tr>
	 	<td></td>
	 	<td></td>
	 	<td></td>
	 </tr>
	</table>

</body>
</html>