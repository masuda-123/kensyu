<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
	String error_question = String.valueOf(request.getAttribute("error_question"));
	String error_answer = String.valueOf(request.getAttribute("error_answer"));
%>
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
		    <% if(error_question != "null") { %>
		    	<li>
		    		<%= error_question %>
		    	</li>
		    <% } %>
		    </li>
		    	<label for="question">問題:</label>
		    	<input type="text" id="question" name="question">
		    </li>
		    <% if(error_answer != "null") { %>
		    	<li>
		    		<%= error_answer %>
		    	</li>
		    <% } %>
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