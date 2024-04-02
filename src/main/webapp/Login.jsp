<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./Login" method="post">
	  <ul>
	    <li>
	      <label for="user_id">ID:</label>
	      <input type="number" id="user_id" name="user_id">
	    </li>
	    <li>
	      <label for="password">パスワード:</label>
	      <input type="password" id="password" name="password">
	    </li>
	    <div>
	    <input type="submit" value="ログイン">
	    </div>
	  </ul>
	</form>
</body>
</html>