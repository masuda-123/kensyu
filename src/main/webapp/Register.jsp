<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
</head>

<body>
	<ul>
		<li>
			<button onclick="location.href='./Top'">top</button>
			<button>logout</button>
		</li>
		<form action="./Confirm" method="post">
		    <li>
		    	<label for="question">問題:</label>
		    	<input type="text" id="question" name="question">
		    </li>
		    </li>
		    <li>
		    	<label for="answer">答え:</label>
		      	<input type="text" id="answer" name="answer">
		    </li>
		    
		    <div>
		    	<button type="button" onclick="history.back()">戻る</button>
		    	<input type="submit" value="確認">
		    	<button>追加</button>
		    </div>
		</form>
	</ul>
</body>
</html>