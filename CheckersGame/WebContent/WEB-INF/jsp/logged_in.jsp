<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

initialize global variables (just as session variables)
including: player id, first name, last name, email.





<c:forEach var="challenge" items="${challenges }">
${challenge.firstName}${" "}${challenge.lastName}${" "}<br/>
</c:forEach>


<c:forEach var="player" items="${players }">
${player.userN}${" "}$<a href="<c:url value="/CheckersGame/makeCH?id=${player.id}"/>">challenge him!!</a><br/>
</c:forEach>


<c:forEach var="game" items="${games }">
${game.firstName}${" "}${game.lastName}${" "}<br/>
</c:forEach>





</body>
</html>