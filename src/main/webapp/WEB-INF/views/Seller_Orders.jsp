<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="icon" type="image/png"
	href="<c:url value='/resources/images/ecommerce-logo-1-dribbble.png'/> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/Seller_Orders.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerSnackbar.css' />" />
</head>
<body>
	<jsp:include page="SellerHeader.jsp"></jsp:include>
	<div class="w-100 table-wrapper mx-auto">
		<form action="/ecommerce/seller/changeStatus" method="POST">
			<table class="table table-hover">
				<thead class="thead-light">
					<tr>
						<th>Order Item ID</th>
						<th>Product Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Order Date</th>
						<th>Delivery Address</th>
						<th>Status</th>
						<th id="selectAll">Select<input class="selectAllCheckBox"
							type="checkbox" onchange="checkAllOrderId(this)" /></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="orderItem" items="${orderItems}">
						<tr>
							<td>${orderItem.id}</td>
							<td>${orderItem.warehouseProduct.product.name}</td>
							<td>${orderItem.quantity}</td>
							<td>${orderItem.price}</td>
							<td>${orderItem.order.orderDate}</td>
							<td>${orderItem.order.address}</td>
							<td>${orderItem.status}</td>
							<c:if
								test="${'ORDERED' == orderItem.status || 'DISPATCHED' == orderItem.status}">
								<td><input id="${orderItem.id}" class="form-control"
									type="checkbox" name="orderItemId" value="${orderItem.id}" />
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="form-group status-btn">
				<button id="changeStatusBtn" class="btn btn-outline-primary"
					onclick="return checkOrderIds()" type="submit">Change
					Status</button>
			</div>
			<div id="snackbar" style="left: 45%;">Please Select atleast one
				Order to Proceed</div>
		</form>
	</div>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
	<script>
		showSelectAll();
	</script>
</body>
</html>