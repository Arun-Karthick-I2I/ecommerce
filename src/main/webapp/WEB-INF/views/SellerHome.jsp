<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerHome.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerSnackbar.css' />" />
</head>
<body>
	<jsp:include page="SellerHeader.jsp"></jsp:include>
	<div class="buttonWrapper">
	</div>
	<c:if test="${showAddress}">
		<div class="w-75 mx-auto addressWrapper" id="addressWrapper">
			<table class="table table-hover">
				<thead class="thead-light">
					<tr>
						<th scope="col">Address</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="address" items="${addresses}">
						<tr>
							<form method="POST">
								<td>${address}</td>
								<td><input type="hidden" name="addressId"
									value="${address.id}" />
									<button class="btn btn-primary btn-sm" type="submit"
										formaction="/ecommerce/editAddress">Edit</button> <c:if
										test="${1 < addresses.size()}">
										<button class="btn btn-danger btn-sm" type="submit"
											formaction="/ecommerce/removeAddress"
											onclick="return confirm('Are you sure want to delete this address')">Delete</button>
									</c:if></td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<c:if test="${showProducts}">
	
	</c:if>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
	<c:if test="${null != message}">
		<div id="snackbar">${message}</div>
		<script>
			showSnackBar();
		</script>
		<c:remove var="message" />
	</c:if>
	<script>
		showProductForm();
	</script>
</body>
</html>
