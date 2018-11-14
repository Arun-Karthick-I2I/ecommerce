<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Customers</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminDisplayCustomers.css' />">
</head>
<body>
	<div class="title">
		<h2> Orders </h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="name"
				placeholder="Enter Order ID to be searched">
		 	<button class="btn btn-default" type="submit"
				formaction="/ecommerce/admin/displayOrders" formmethod="post">
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
							<td> ${order.id} </td>
							<td> ${orderIem.warehouseProduct.product.name} </td>
							<td> ${orderItem.quantity} </td>
							<td> ${orderItem.price} </td>
							<td> ${order.orderDate} </td>
							<td> ${orderIem.warehouseProduct.seller.name} </td>
							<td> ${order.customer.name} </td>
							<td> ${order.address} </td>
							<td> ${order.orderItem.status} </td>
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