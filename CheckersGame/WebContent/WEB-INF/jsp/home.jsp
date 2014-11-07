<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h1>Welcome to checker game</h1>
<h2>Login</h2>
	<div>
		<form  action="/CheckersGame/doLogin" method="post" >
			Username:<input type="text" name="username" required/><br />
			Password:<input type="password" name="password" required/><br />
			<input type="submit">
		</form>
	</div>
<a href="/CheckersGame/register">Register</a>
</body>
</html>