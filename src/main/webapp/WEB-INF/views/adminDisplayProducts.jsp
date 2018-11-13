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
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="name"
				placeholder="Enter Product Name to be searched">
		 	<button class="btn btn-default" type="submit"
				formaction="/ecommerce/product/searchByName" formmethod="post">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<div class="card-deck">
		<c:forEach var="product" items="${products}">
			<form>
  				<div class="card">
	    			<img class="card-img-top" src="data:image/jpg;base64,${product.base64Image}">
    		    	<div class="card-body">
      					<h5 class="card-title">${product.name}</h5>
      					<p class="card-text">&#8377;${product.warehouseProducts[0].price}</p>
      					<p class="card-text">
	      					<input type="hidden" name="id" value="${product.category.id}"/>
    	  					<button class="btn btn-default" formmethod="post"
								formaction="/ecommerce/category/displayProducts">
								${product.category.name}
							</button>
      					</p>
    				</div>
	 	    	</div>
 	    	</form>
 	    </c:forEach>
	</div>
</body>
</html>