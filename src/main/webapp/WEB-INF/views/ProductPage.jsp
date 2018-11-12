<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PandaZone</title>

<link rel="stylesheet"
	href="<c:url value='/resources/css/ProductPage.css' />">

</head>
<body>
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<div class="product">
		<form>
			<div id="left">
				<div class="image">
					<img class="product-image"
						src="data:image/jpg;base64,${product.base64Image}" />
				</div>
				<div class="product-button">
					<input type="hidden" name="id" value="" />
					<c:if test="${not empty customer}">
					<button type="submit" formaction="/ecommerce/addCart"
						formmethod="post" class="btn btn-warning btn-block btn-lg">
						<span class="glyphicon glyphicon-shopping-cart"></span> &nbsp;<b>ADD
							TO CART</b>
					</button>
					</c:if>
				    <c:if test="${empty customer}">
					<button onclick="isLoggedIn()" class="btn btn-warning btn-block btn-lg">
						<span class="glyphicon glyphicon-shopping-cart"></span> &nbsp;<b>ADDDD
							TO CART</b>
					</button>
					</c:if>
					&nbsp;&nbsp;
					<button type="submit" class="btn btn-danger btn-block btn-lg">
						<i class="fa fa-bolt"></i>&nbsp; <b>BUY NOW</b>
					</button>
				</div>
			</div>

			<div id="right">
				<div class="product-details">
					<input type="hidden" value=${product.id } name="id" /> <Strong></Strong>
					<p>${product.name}</p>
					<c:if test="${null == product.rating}">
						<p>Be the first to rate it.</p>
					</c:if>
					<c:if test="${null ne product.rating}">
						<Strong>${product.rating}<img class="verified-icon"
							src="<c:url value='/resources/images/square-grunge-red-verified-stamp-vector-16216568.jpg' />" /></Strong>
					</c:if>

					<p>
						<Strong>${product.description}</Strong>
					</p>
				</div>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
function isLoggedIn(event) {
    alert("hihihihihihihihihihihi");
    event.preventDefault();
    return;
    	
}
</script>


</html>