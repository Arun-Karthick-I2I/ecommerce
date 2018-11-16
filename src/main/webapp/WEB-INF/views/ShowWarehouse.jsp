<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ShowWarehouse.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerSnackbar.css' />" />
</head>
<body>
	<jsp:include page="SellerHeader.jsp"></jsp:include>
	<div class="container">
		<c:forEach var="warehouseProduct" items="${warehouseProducts}">
			<div class="card">
				<div class="flip-box">
					<div class="flip-box-inner">
						<div class="flip-box-front">
							<img class="card-img-top"
								src="data:image/jpg;base64,${warehouseProduct.product.base64Image}">
								<h5 class="card-title">${warehouseProduct.product.name}</h5>
						</div>
						<div class="flip-box-back">
							<div class="card-body">
								<h5 class="card-title">${warehouseProduct.product.name}</h5>
								<p class="card-text">Your Price:</p>
								<p class="card-text">&#8377;${warehouseProduct.price}</p>
								<p class="card-text">Stock Available:</p>
								<p class="card-text">${warehouseProduct.quantity}</p>
								<form action="/ecommerce/seller/editWarehouseProduct" method="POST">
									<input type="hidden" name="warehouseProductId" value="${warehouseProduct.id}" />
									<button class="btn btn-sm btn-info" type="submit" > Update </button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>	
		</c:forEach>
	</div>
	<div id="snackbar">Hover on the product for more details</div>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
	<script type="text/javascript">
		showSnackBar();
	</script>
</body>
</html>
