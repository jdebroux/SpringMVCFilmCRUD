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
	<h2>Keyword Search Results</h2>
	<br>
	<c:choose>
		<c:when test="${! empty filmByKeyword}">
			<ul>
				<c:forEach var="film" items="${filmByKeyword}">
					<li>${film}</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No films found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>