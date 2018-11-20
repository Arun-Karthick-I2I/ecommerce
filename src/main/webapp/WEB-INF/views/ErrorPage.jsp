<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ErrorPage.css' />" />
</head>
<body>
	<c:set var="statusCode" value="<%=response.getStatus()%>" />
	<c:if test="${404 == statusCode}">
		<div class="errorContent">
			<img class="404-image" alt="Error 404"
				src="<c:url value='/resources/images/404error.jpg' />">
			<h3>We couldn't find the page you are looking for</h3>
		</div>
	</c:if>
	<c:if test="${405 == statusCode}">
		<div class="errorContent">
			<img class="errorImage" alt="Error!!!"
				src="<c:url value='/resources/images/something went wrong.gif' />">
		</div>
	</c:if>
	<c:if test="${500 <= statusCode}">
		<div class="errorContent">
			<img class="errorImage" alt="Error!!!"
				src="<c:url value='/resources/images/something went wrong.gif' />">
			<h3 class="errorText">Oops!! An Error Occurred!!! Please Try
				Again Later</h3>
		</div>
	</c:if>
	<div class="homeButtonWrapper">
		<form action="/ecommerce/" method="GET">
			<button class="btn btn-outline-primary" type="submit">Take
				Me Back Home</button>
		</form>
	</div>
</body>
</html>

