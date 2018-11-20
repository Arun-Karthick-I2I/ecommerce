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

					<input type="hidden" name="id"
						value="${product.warehouseProducts[0].id}" /> <input
						type="hidden" name="productId" value="${product.id}" />
					<c:if test="${product.warehouseProducts[0].quantity > 0}">
						<c:if test="${not empty customer}">

							<button type="submit" formaction="/ecommerce/addCart"
								formmethod="post" class="btn btn-warning btn-block btn-lg">
								<i class="fa fa-shopping-cart"></i>&nbsp;<b>ADD
									TO CART</b>
							</button>
					&nbsp;&nbsp;
					<button type="submit" formaction="/ecommerce/orderProduct"
								formmethod="post" class="btn btn-danger btn-block btn-lg">
								<i class="fa fa-bolt"></i>&nbsp; <b>BUY NOW</b>
							</button>
						</c:if>
						<c:if test="${empty customer}">
							<button type="button" onclick="isLoggedIn()"
								class="btn btn-warning btn-block btn-lg">
								<i class="fa fa-shopping-cart"></i>&nbsp; <b>ADD
									TO CART</b>
							</button>
					&nbsp;&nbsp;
					<button type="button" onclick="isLoggedIn()"
								class="btn btn-danger btn-block btn-lg">
								<i class="fa fa-bolt"></i>&nbsp; <b>BUY NOW</b>
							</button>
						</c:if>
					</c:if>
					<c:if test="${product.warehouseProducts[0].quantity == 0}">
						<div class="outOfStock">OUT OF STOCK</div>
					</c:if>
				</div>
			</div>

			<div id="right">
				<div class="product-details">
					<input type="hidden"
						value=${product.warehouseProducts[0].id
						} name="id" />
					<div class="productName">
						<Strong>${product.name}</Strong>
						<div>#AvailableOnEcommerce</div>
					</div>
					<label>Price :</label>
					<div class="price">&nbsp;&#8377
						${product.warehouseProducts[0].price}</div>
					<div class="productRating">
						<c:if test="${null ne product.rating}">
							<div>${product.rating}</div>
						</c:if>
					</div>
					<label>Specification :</label>
					<div class="description">${product.description}</div>
					<label>Seller :</label>
					<div class="seller">
						${product.warehouseProducts[0].seller.name}
						<c:if test="${fn:length(product.warehouseProducts) > 1}">
			        	and<button type="button" class="btn btn-default"
								id="sellersModalButton" data-target="#sellers"
								data-toggle="modal">
								<a> view more sellers</a>
							</button>
						</c:if>
					</div>

					<br /> <label>Service :</label>
					<div class="service">Cash On Delivery Available.</div>
					<div class="service">10 days replacement policy.</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="CustomerFooter.jsp"></jsp:include>
	<div class="modal fade" id="sellers" role="dialog">
		<div class="modal-dialog modal-md modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<table class="sellers">
						<tr>
							<th>Seller</th>
							<th>Price</th>
							<th></th>
						</tr>
						<c:forEach var="warehouseProduct"
							items="${product.warehouseProducts}">
							<form method="post">
								<tr>
									<td>${warehouseProduct.seller.name}</td>
									<td>${warehouseProduct.price}</td>
									<td>
										<div class="product-button">
											<input type="hidden" name="id" value="${warehouseProduct.id}" />
											<input type="hidden" name="productId" value="${product.id}" />
											<c:if test="${warehouseProduct.quantity > 0}">
												<c:if test="${not empty customer}">
													<button type="submit" formaction="/ecommerce/addCart"
														formmethod="post" class="btn btn-warning">
														<span class="glyphicon glyphicon-shopping-cart"></span>
														&nbsp;<b>ADD TO CART</b>
													</button>
												&nbsp;
												<button type="submit" formaction="/ecommerce/orderProduct"
														formmethod="post" class="btn btn-danger">
														<i class="fa fa-bolt"></i>&nbsp; <b>BUY NOW</b>
													</button>
												</c:if>
												<c:if test="${empty customer}">
													<button type="button" onclick="isLogged()"
														class="btn btn-warning">
														<i class="fa fa-shopping-cart"></i>&nbsp;
														<b>ADD TO CART</b>
													</button>
												&nbsp;
												<button type="button" onclick="isLogged()"
														class="btn btn-danger">
														<i class="fa fa-bolt"></i>&nbsp; <b>BUY NOW</b>
													</button>
												</c:if>
											</c:if>
											<c:if test="${warehouseProduct.quantity == 0}">
												Out of Stock.
											</c:if>
										</div>
									</td>
								</tr>
							</form>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>

<script type="text/javascript">
	function isLoggedIn(event) {
		$("#Customerlogin").modal("show");
	}
	function isLogged(event) {
		$("#sellers").modal("hide");
		$("#Customerlogin").modal("show");
	}
</script>

</html>