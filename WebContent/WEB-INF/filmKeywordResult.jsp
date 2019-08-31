<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KeywordResults</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h2>Keyword Search Results</h2>
	<br>
	<c:choose>
		<c:when test="${! empty filmByKeyword}">
			<ul>
				<c:forEach var="film" items="${filmByKeyword}">
					<h3>Film Details:</h3>
					<li>${film}</li>
					<h4>Actors in Film:</h4>
					<c:forEach var="actor" items="${film.actors}">
						<li>"${actor}"</li>
					</c:forEach>
					<br>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No films found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>