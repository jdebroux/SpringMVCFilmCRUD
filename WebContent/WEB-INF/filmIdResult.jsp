<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IDResults</title>
</head>
<body>
	<h2>Film ID Search Results</h2>
	<c:choose>
		<c:when test="${! empty filmById}">
			<h3>Film Information:</h3>
			<ul>
				<li>${filmById}</li>
			</ul>
			<h3>Actors in Film:</h3>
			<ul>
				<c:forEach var="actor" items="${actorsByFilmId}">
					<li>${actor}</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>