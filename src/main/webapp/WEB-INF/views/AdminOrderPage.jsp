<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminOrderPage.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminSnackbar.css' />">
</head>
<body>
	<div class="title">
		<h2>Orders</h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="id"
				placeholder="Enter Order ID to be searched" pattern="^[0-9]*$"
				required>
			<button class="btn btn-default" type="submit"
				formaction="/ecommerce/admin/searchByOrderId" formmethod="get">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<div class="table-wrapper" id="orders">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Ordered Date</th>
					<th>Seller</th>
					<th>Customer</th>
					<th>Delivery Address</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<c:forEach var="orderItem" items="${order.orderItems}">
						<form action="admin" method="Post">
							<tr>
								<td class="id">${order.id}</td>
								<td class="productName" data-toggle="tooltip"
									data-placement="bottom"
									title="ID: ${orderItem.warehouseProduct.product.id},
								Name: ${orderItem.warehouseProduct.product.name},
								Price: ${orderItem.warehouseProduct.price},
								Category: ${orderItem.warehouseProduct.product.category.name}">
									${orderItem.warehouseProduct.product.name}</td>
								<td class="quantity">${orderItem.quantity}</td>
								<td class="price">${orderItem.price}</td>
								<td class="orderDate">${order.orderDate}</td>
								<td class="sellerName" data-toggle="tooltip"
									data-placement="bottom"
									title="ID: ${orderItem.warehouseProduct.seller.id},
								Name: ${orderItem.warehouseProduct.seller.name},
								Contact No: ${orderItem.warehouseProduct.seller.mobileNumber},
								Email ID: ${orderItem.warehouseProduct.seller.emailId}">
									${orderItem.warehouseProduct.seller.name}</td>
								<td class="customerName" data-toggle="tooltip"
									data-placement="bottom"
									title="ID: ${order.customer.id},
								Name: ${order.customer.name},
								Contact No: ${order.customer.mobileNumber},
								Email ID: ${order.customer.emailId}">
									${order.customer.name}</td>
								<td class="address">${order.address}</td>
								<td class="status">${orderItem.status}</td>
							</tr>
						</form>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<button onclick="javascript:demoFromHTML()">PDF</button>
	
</body>
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jspdf.debug.js'/>"></script>
<script>
function demoFromHTML() {
    var pdf = new jsPDF('landscape', 'pt', 'a2');
    source = $('#orders')[0];

    specialElementHandlers = {
        '#bypassme': function (element, renderer) {
            return true
        }
    };
    margins = {
        top: 80,
        bottom: 60,
        left: 40,
        width: 522
    };
    pdf.fromHTML(
    source, 
    margins.left, 
    margins.top, {
        'width': margins.width, 
        'elementHandlers': specialElementHandlers
    },

    function (dispose) {
        pdf.save('Orders.pdf');
    }, margins);
}
</script>

<script src="<c:url value='/resources/js/Admin.js' />"></script>
<c:if test="${null != message}">
	<div id="snackbar">${message}</div>
	<script>
		showSnackBar();
	</script>
</c:if>
</html>