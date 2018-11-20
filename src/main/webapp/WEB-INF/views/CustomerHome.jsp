<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ecommerce</title>
<link rel="icon" type="image/png"
	href="<c:url value='/resources/images/ecommerce-logo-1-dribbble.png' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/CustomerHome.css' />">

</head>
<body>
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<div class="customerHome">
		<c:if test="${fn:length(products) >= 1}">
			<c:forEach var="product" items="${products}">
				<form class="product">
					<div class="container">
						<div class="card">
							<input type="hidden" name="id" value="${product.id}" />
							<button type="submit" formmethod="post"
								formaction="/ecommerce/customer/productPage/">
								<img class="card-img-top zoom"
									src="<c:url value='data:image/jpg;base64,${product.base64Image}'/>"
									alt="product image" />
							</button>
							<div class="card-body">
								<button type="submit" formmethod="post"
									formaction="/ecommerce/customer/productPage/">
									<h4 class="card-title">${product.name}</h4>
								</button>
								<p class="card-text">
									<b>₹&nbsp;${product.warehouseProducts[0].price}</b><br /> <input
										type="hidden" name="categoryId" value="${product.category.id}">
									<button class="btn btn-default btn-sm"
										formaction="/ecommerce/customer/products" formmethod="post">
										<a>View Similar Products</a>
									</button>
								</p>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</c:if>

		<c:if test="${fn:length(products) < 1}">
			<div class="no-result">
				<img
					src="<c:url value='/resources/images/error-no-search-results_e83b58.png'/>"
					alt="No result found" />
				<div class="card-body">
					<h4>
						<Strong>Sorry, no results found!</Strong>
					</h4>
					<p>Please check the spelling or try searching for something
						else</p>
				</div>
			</div>
		</c:if>
	</div>
	<jsp:include page="CustomerFooter.jsp"></jsp:include>
</body>

<script>
	showSlides();

	function showSlides() {
		var i;
		var slides = document.getElementsByClassName("mySlides");
		var dots = document.getElementsByClassName("dot");
		for (i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		slideIndex++;
		if (slideIndex > slides.length) {
			slideIndex = 1
		}
		for (i = 0; i < dots.length; i++) {
			dots[i].className = dots[i].className.replace(" active", "");
		}
		slides[slideIndex - 1].style.display = "block";
		dots[slideIndex - 1].className += " active";
		setTimeout(showSlides, 2000); // Change image every 2 seconds
	}
</script>

</html>