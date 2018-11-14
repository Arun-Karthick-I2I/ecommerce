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
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
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
			<div class="deliveryAddressTitle">
				<b>DELIVERY ADDRESS</b>
				<button type="button" onclick="openDeliveryAddress()"
					class="btn btn-default">
					<b> &nbsp;&nbsp;CHANGE</b>
				</button>
			</div>
			<div id="addresses" class="deliveryAddresses">
				<div class="address">
					<c:forEach var="address" items="${customer.user.addresses}">
						<label><input type="radio" name="addressId"
							value="${address.id}">&nbsp;&nbsp;${address}</label>
					</c:forEach>
					<br />
					<button class="btn btn-warning" onclick="openPayment()">
						<b>DELIVER HERE</b>
					</button>
					<br />
				</div>
				<div class="newAddress">
					<button type="button" class="btn btn-default btn-block">
						<i class="fa fa-plus" aria-hidden="true"></i> <b>
							&nbsp;&nbsp;ADD NEW DELIVERY ADDRESS</b>
					</button>
				</div>
			</div>
		</div>


		<div class="payment">
			<div class="paymentTitle">
				<b>PAYMENT OPTIONS</b>
				<button type="button" onclick="openPayment()"
					class="btn btn-default">
					<b> &nbsp;&nbsp;CHANGE</b>
				</button>
			</div>
			<div id="paymentOption" class="paymentOptions">
				<div class="modeOfPayment">
					<label> <input type="radio" name="modeOfPayment"
						value="Cash On Delivery">&nbsp;&nbsp;Cash On Delivery
					</label><br /> <label><input type="radio" name="modeOfPayment"
						value="Credit / Debit Card">&nbsp;&nbsp;Credit / Debit
						Card</label> <br />
					<label><input type="radio" name="modeOfPayment"
						value="NetBanking">&nbsp;&nbsp;NetBanking</label> <br />
					<label><input type="radio" name="modeOfPayment"
						value="Phonepe / BHIM UPI">&nbsp;&nbsp;Phonepe/BHIM UPI</label>
				</div>
			</div>
			<div class="placeOrderButton">
			<button type="submit" id="makeOrder" class="btn btn-default btn-lg">
				<b>&nbsp;&nbsp;PLACE ORDER</b>
			</button>
			<button type="submit" id="cancelOrder" class="btn btn-default btn-lg">
				<b>&nbsp;&nbsp;CANCEL</b>
			</button>
			<div>
		</div>



	</div>
</body>
<script>
	function openPayment(event) {
		$("#addresses").hide();
		$("paymentOption").show();
	}

	function openDeliveryAddress(event) {
		$("#paymentOption").hide();
		$("#addresses").show();
	}
</script>

</html>