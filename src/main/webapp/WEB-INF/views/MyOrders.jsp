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
	href="<c:url value='/resources/css/MyOrders.css' />">

<style>
.myOrders>.table>tbody>tr>td {
	vertical-align: middle;
}
</style>

</head>
<body>
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<div class="myOrders">
		<c:if test="${fn:length(orders) > 0}">
			<c:forEach var="order" items="${orders}">
				<table class="table">
					<thead>
						<tr class="header">
							<td colspan="6">
								<div class="orderid">ORDER ID : &nbsp;${order.id}</div>
							<td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="orderItem" items="${order.orderItems}">
							<tr>
								<td class="productImage">
									<div class="productImage">
										<img class="image"
											src="<c:url value='data:image/jpg;base64,${orderItem.warehouseProduct.product.base64Image}'/>"
											alt="image" />
									</div>
								</td>
								<td class="productName">
									<div class="productName">
										<b>${orderItem.warehouseProduct.product.name}</b><br /> <br />
										<div class="seller">Seller :
											${orderItem.warehouseProduct.seller.name}</div>
									</div>
								</td>
								<td class="productQuantity">
									<div class="productQuantity">
										<label>Items :&nbsp;&nbsp;${orderItem.quantity}</label>
									</div>
								</td>
								<td class="productPrice"><b>Rs.</b>&nbsp;${orderItem.price}
								</td>
								<td class="orderStatus">${orderItem.status}</td>
								<td class="orderCancel">
									<form>
										<input type=hidden name="orderId" value="${order.id}" /> <input
											type=hidden name="orderItemId" value="${orderItem.id}" />
										<c:if test="${orderItem.status == 'ORDERED'}">
											<button type="submit" class="btn btn-default"
												formmethod="post" formaction="/ecommerce/customer/cancelOrder">
												Cancel&nbsp;&nbsp;<i class="fa fa-close"></i>
											</button>
										</c:if>
										<c:if
											test="${orderItem.status == 'DELIVERED' && returnDates[order.id] > today}">
											<button type="submit" class="btn btn-warning"
												formmethod="post" formaction="/ecommerce/customer/returnOrder">
												Return &nbsp;&nbsp;<i class="fa fa-close"></i>
											</button>
										</c:if>
									</form>
								</td>
							</tr>
						</c:forEach>

						<tr class="table-footer">
							<td colspan="3" class="orderDate"><b>Ordered On :</b>
								&nbsp;${order.orderDate}
							<td>
							<td colspan="3" class="orderPrice"><b>Order total :</b>
								&nbsp;Rs.${order.price}
							<td>
						</tr>
						<tr class="order-address">
							<td colspan="6"><b>Delivered to : &nbsp;</b>
								${order.address}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
		</c:if>
		<c:if test="${fn:length(orders) == 0}">
			<div class="no-result">
				<img src="<c:url value='/resources/images/no-order.png'/>"
					alt="No result found" />
				<div class="card-body">
					<h4>
						<b>You haven't ordered anything yet!!</b>
					</h4>
				</div>
			</div>
		</c:if>
	</div>
	<jsp:include page="CustomerFooter.jsp"></jsp:include>
</body>
</html>