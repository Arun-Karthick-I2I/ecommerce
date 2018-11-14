<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pandaZone</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/OrderPage.css' />">
<style>
.orderProducts>.table>tbody>tr>td {
	vertical-align: middle;
}
</style>
</head>
<body>
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<div class="orderProducts">
		<table class="table">
			<tr>
				<th class="headingProduct">Product</th>
				<th class="headingName">Name</th>
				<th class="headingQuantity">Quantity</th>
				<th class="headingPrice">Price</th>
			</tr>
			<tbody>
				<c:forEach var="warehouseProduct" items="${warehouseProducts}"
					varStatus="loop">
					<tr>
						<form class="products">
							<td class="productName"><div class="productImage">
									<img class="image"
										src="<c:url value='data:image/jpg;base64,${warehouseProduct.product.base64Image}'/>"
										alt="image" />
									<div></td>
							<td class="productName">${warehouseProduct.product.name}</td>
							<td class="productQuantity">${quantities[loop.index]}</td>
							<td class="productPrice">${warehouseProduct.price}</td>
						</form>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3"></td>
					<td><label>Total Price :</label>&nbsp;&nbsp;<b>Rs.
							${totalPrice} </b></td>
				<tr>
			</tbody>
		</table>
		<div class="deliveryAddress">
		    <div class="deliveryAddressTitle"><b>DELIVERY ADDRESS</b>
		    </div>
		    <div class="deliveryAddresses">
		    	<c:forEach var="address" items="${customer.addresses}">
		    	<div class="deliveryAddressTitle"><b>DELIVERY ADDRESS</b>
		    	</div>
		    	</c:forEach>
		    	<div class="deliveryAddressTitle"><b>DELIVERY ADDRESS</b>
		    	</div>
		    </div>
		</div>
	</div>
</body>


</html>