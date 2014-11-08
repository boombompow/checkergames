<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

initialize global variables (just as session variables)
including: player id, first name, last name, email.

<br/><br/><br/><br/><br/>

<h2>games history</h2>
<c:forEach var="game" items="${games }">
${game.first_player.email}${" "}${game.second_player.email}${" "}${game.current_player.email}${" "}${game.status}${" "}<br/>
</c:forEach>


<h2>challenges history</h2>
<c:forEach var="challenge" items="${challenges }">
${challenge.challenger.email}${" "}${challenge.challengee.email}${" "}${challenge.status}${" "}<br/>
</c:forEach>


<h2>user list</h2>
<c:forEach var="p" items="${players }">
<c:if test="${p.id != player.id}">
	${p.firstN}${" "}${p.lastN}${" "}<a href="<c:url value="/CheckersGame/makeCH?id=${p.id}"/>">challenge him!!</a><br/>
</c:if>
</c:forEach>








</body>
</html>