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

.modal-dialog {
	left: 0%;
}
</style>
</head>
<body onload="initialPage()">
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<form>
		<div class="orderProducts">
			<input type="hidden" name="buyProduct" value="${buyProduct}" />
			<table class="table">
				<tr>
					<th class="headingProduct">Product</th>
					<th class="headingName">Name</th>
					<th class="headingQuantity">Quantity</th>
					<th class="headingPrice">Price</th>
				</tr>
				<tbody>
					<c:forEach var="warehouseProduct" items="${warehouseProducts}" >
						<input type="hidden" name="id" value="${warehouseProduct.id}">
						<tr>
							<form class="products">
								<td class="productName"><div class="productImage">
										<img class="image"
											src="<c:url value='data:image/jpg;base64,${warehouseProduct.product.base64Image}'/>"
											alt="image" />
									</div></td>
								<td class="productName">${warehouseProduct.product.name}</td>
								<td class="productQuantity">${quantities[warehouseProduct.id]}</td>
								<td class="productPrice">${warehouseProduct.price * quantities[warehouseProduct.id]}</td>
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
						id="changeDeliveryAddress" class="btn btn-default">
						<b> &nbsp;&nbsp;CHANGE</b>
					</button>
				</div>
				<div id="addresses" class="deliveryAddresses">
					<div class="address">
						<c:forEach var="address" items="${customer.user.addresses}">
							<label><input type="radio" name="addressId"
								value="${address.id}">&nbsp;&nbsp;${address}</label>
							<br />
						</c:forEach>
						<br />
						<button type="button"  class="btn btn-warning" onclick="selectDeliveryAddress()">
							<b>DELIVER HERE</b>
						</button>
						<br /> <br />
					</div>
				</div>
			</div>

			<div class="payment">
				<div class="paymentTitle">
					<b>PAYMENT OPTIONS</b>
					<button type="button" onclick="openPayment()"
						id="changePaymentOption" class="btn btn-default">
						<b> &nbsp;&nbsp;CHANGE</b>
					</button>
				</div>
				<div id="paymentOption" class="paymentOptions">
					<div class="modeOfPayment">
						<label> <input type="radio" name="modeOfPayment"
							value="Cash On Delivery" checked>&nbsp;&nbsp;Cash On
							Delivery
						</label><br /> <label><input type="radio" name="modeOfPayment"
							value="Credit / Debit Card">&nbsp;&nbsp;Credit / Debit
							Card</label> <br /> <label><input type="radio"
							name="modeOfPayment" value="NetBanking">&nbsp;&nbsp;NetBanking</label>
						<br /> <label><input type="radio" name="modeOfPayment"
							value="BHIM UPI">&nbsp;&nbsp;BHIM UPI</label>
					</div>
					<div class="placeOrderButton">
						<button type="submit" id="makeOrder" formmethod="post" formaction="/ecommerce/placeOrder"
							class="btn btn-default btn-lg">
							<b>&nbsp;&nbsp;PLACE ORDER</b>
						</button>
						<button type="submit" id="cancelOrder" formmethod="get"
							formaction="/ecommerce/" class="btn btn-default btn-lg">
							<b>&nbsp;&nbsp;CANCEL</b>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>

<script>
	function initialPage() {
		$("#paymentOption").hide();
		$("#changePaymentOption").hide();
		$("#addresses").show();
		$("#changeDeliveryAddress").hide();
	}

	function openPayment(event) {
		$("#changePaymentOption").hide();
		$("#changeDeliveryAddress").show();
		$("#addresses").hide();
		$("#paymentOption").show();
	}

	function selectDeliveryAddress(event) {

		var checked = false;
		var c = document.getElementsByTagName('input');
		for (var i = 0; i < c.length; i++) {
			if (c[i].type == 'radio' && c[i].checked == true
					&& c[i].name == 'addressId') {
				checked = true;
			}
		}
		if (checked == false) {
			alert("select delivery address");
		} else {
			$("#changePaymentOption").hide();
			$("#changeDeliveryAddress").show();
			$("#addresses").hide();
			$("#paymentOption").show();
		}
	}

	function openDeliveryAddress(event) {
		$("#changePaymentOption").show();
		$("#changeDeliveryAddress").hide();
		$("#paymentOption").hide();
		$("#addresses").show();
	}
</script>

</html>