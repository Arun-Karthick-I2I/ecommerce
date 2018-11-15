<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Customers</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminOrderPage.css' />">
</head>
<body>
	<div class="title">
		<h2> Orders </h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="id"
				placeholder="Enter Order ID to be searched">
		 	<button class="btn btn-default" type="submit"
				formaction="/ecommerce/admin/searchByOrderId" formmethod="post">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th> Order ID </th>
				<th> Product Name </th>
				<th> Quantity </th>
				<th> Price </th>
				<th> Ordered Date </th>
				<th> Seller </th>
				<th> Customer </th>
				<th> Delivery Address </th>
				<th> Status </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${orders}" var="order">
				<c:forEach var="orderItem" items="${order.orderItems}"> 
					<form action="admin" method="Post">
						<tr>
							<td class="id"> ${order.id} </td>
							<td class="productName"> ${orderItem.warehouseProduct.product.name} </td>
							<td class="quantity"> ${orderItem.quantity} </td>
							<td class="price"> ${orderItem.price} </td>
							<td class="orderDate"> ${order.orderDate} </td>
							<td class="sellerName"> ${orderItem.warehouseProduct.seller.name} </td>
							<td class="customerName"> ${order.customer.name} </td>
							<td class="address"> ${order.address} </td>
							<td class="status"> ${orderItem.status} </td>
						</tr>
					</form>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
</body>
<c:if test="${null != message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</html>