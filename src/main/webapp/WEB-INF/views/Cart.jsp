<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pandaZone</title>
<link rel="stylesheet" href="<c:url value='/resources/css/Cart.css' />">
<style>
.cartPage> .cartProduct>.table>tbody>tr>td {
	vertical-align: middle;
}
</style>


</head>
<body>
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<div class="cartPage">
		<div class="cartTitle">
			MY CART&nbsp;(${fn:length(customer.cartItems)})
			<div class="to"></div>
		</div>
		<c:if test="${fn:length(customer.cartItems) > 0}">
			<div class="cartProduct">
				<table class="table">
					<tbody>
						<c:forEach var="cartItem" items="${customer.cartItems}">
							<form>
								<input type="hidden" name="id" value="${cartItem.id}">
							<tr>
								<td class="productImage">
									<div class="productImage">
										<img class="image"
											src="<c:url value='data:image/jpg;base64,${cartItem.warehouseProduct.product.base64Image}'/>"
											alt="image" />
									</div>
								</td>
								<td class="productName">
									<div class="productName">
										<b>${cartItem.warehouseProduct.product.name}</b><br /> <br />
										<div class="seller">Seller :
											${cartItem.warehouseProduct.seller.name}</div>
									</div>
								</td>
								<td class="productQuantity">
									<div class="productQuantity">
										<button class="btn btn-default" type="submit"
											formmethod="post" formaction="/ecommerce/updateCart"
											onclick="return reduceQuantity(form)">-</button>
										<input type="text" name="quantity"
											value="${cartItem.quantity}" readonly>
										<button class="btn btn-default" type="submit"
											formmethod="post" formaction="/ecommerce/updateCart"
											onclick="return increaseQuantity(form)">+</button>
										<br /> <br /> <label>No of items.</label>
									</div>
								</td>
								<td>
									<div class="productPrice">
										<b>Rs.</b>&nbsp;<input type="text" name="price"
											value="${cartItem.price}" readonly /><br />
									</div>
								</td>
								<td class="removeCart">
									<button type="submit" class="btn btn-danger" formmethod="post"
										formaction="/ecommerce/removeFromCart">REMOVE</button>
								</td>
								<td class="removeCheckout"><input type="checkbox"
									form="allProducts" name="warehouseProductId" checked
									value="${cartItem.warehouseProduct.id}" /><p>Uncheck <br>to<br> Order later</p>
								</td>
							</tr>
							</form>
						</c:forEach>
						<tr>
							<td colspan="6">
								<form id="allProducts">
									<button class="btn btn-warning btn-lg" id="continueShopping"
										type="submit" class="btn btn-default" formmethod="get"
										formaction="/ecommerce/">
										<span><b><i class="fa fa-angle-left"></i> &nbsp;&nbsp;CONTINUE SHOPPING</b></span>
									</button> &nbsp;&nbsp;
									<button class="btn btn-warning btn-lg" id="checkout"
										type="submit" class="btn btn-default" formmethod="post"
										formaction="/ecommerce/buyProducts">
										<span><b>CHECKOUT&nbsp;&nbsp;<i
												class="fa fa-angle-right"></i></b></span>
									</button>
								</form>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${fn:length(customer.cartItems) == 0}">
			<div class="emptyCart">
				<img class="emptyCartimage"
					src="<c:url value='/resources/images/empty-cart_ee6141.png'/>"
					alt="emptyCart" />
				<p>Your Shopping Cart is Empty</p>
			</div>
		</c:if>
	</div>
</body>

<script>
	function increaseQuantity(form) {
		if (parseInt(form.quantity.value) >= 5) {
			event.preventDefault();
			return false;
		} else {
			var quantity = parseInt(form.quantity.value);
			form.quantity.value = quantity + 1;
			form.price.value = (quantity + 1)
					* (parseInt(form.price.value) / quantity);

			return true;
		}
	}

	function reduceQuantity(form) {
		if (parseInt(form.quantity.value) <= 1) {
			event.preventDefault();
			return false;
		} else {
			var quantity = parseInt(form.quantity.value);
			form.quantity.value = quantity - 1;
			form.price.value = (quantity - 1)
					* (parseInt(form.price.value) / quantity);
			return true;
		}
	}
	
</script>
</html>
