<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to checkers game</h1>
<h2>Register</h2>
${msg}
<c:if test="${!(empty msg)}">
	${msg}<br/><br/><br/><br/><br/>
</c:if>

<div>
	<form  action="/CheckersGame/doRegister" method="post" >
		First Name:<input type="text" name="firstname" required/><br />
		Last Name:<input type="text" name="lastname" required/><br />
		Email:<input type="text" name="email" required/><br />
		Username:<input type="text" name="username" required/><br />
		Password:<input type="password" name="password" required/><br />
		<input type="submit">
	</form>
</div>
</body>
</html>