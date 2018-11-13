<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>

<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminDisplayProducts.css' />">

</head>
<body>
	<div class="card-deck">
		<c:forEach var="product" items="${products}">
  			<div class="card">
    			<img class="card-img-top" src="data:image/jpg;base64,${product.base64Image}"
    	    		alt="Card image cap">
    	    	<div class="card-body">
      				<h5 class="card-title">${product.name}</h5>
      				<p class="card-text">&#8377;${product.warehouseProducts[0].price}</p>
      				<p class="card-text"><small class="text-muted">
      				${product.category.name}</small></p>
    			</div>
 	    	</div>
 	    </c:forEach>
	</div>
</body>
</html>