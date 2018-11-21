<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Customers</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminCustomerPage.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminSnackbar.css' />">
</head>
<body>
	<div class="title">
		<h2>Customers</h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="name"
				placeholder="Enter Customer Name to be searched" required>
			<button class="btn btn-default" type="submit"
				formaction="/ecommerce/admin/searchByCustomerName" formmethod="get">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<div class="table-wrapper">
		<table class="table table-striped">
			<thead>
				<tr>
					<th class="row-header">ID</th>
					<th class="row-header">Name</th>
					<th class="row-header">Mobile Number</th>
					<th class="row-header">Email ID</th>
					<th class="row-header">Action</th>
				</tr>
			</thead>
			<tbody id="table-body">
				<c:forEach items="${customers}" var="customer">
					<form action="admin" method="Post">
						<input type="hidden" name="id" value="${customer.id}" />
						<tr>
							<td>${customer.id}</td>
							<td><button class="btn btn-default" formmethod="get"
									formaction="/ecommerce/admin/displayCustomerOrders">
									${customer.name}</button></td>
							<td>${customer.mobileNumber}</td>
							<td>${customer.emailId}</td>
							<td>
								<button type="submit" class="btn btn-danger"
									formaction="/ecommerce/admin/deleteCustomer" formmethod="post">
									<i class="fa fa-trash"></i>
								</button>
							</td>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="footer">
		<Strong>**Note:</Strong> Click on the Customer name to view the Orders
		placed by the Customer
	</div>
</body>
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/Admin.js' />"></script>
<script>
	searchbar();
</script>
<c:if test="${null != message}">
	<div id="snackbar">${message}</div>
	<script>
		showSnackBar();
	</script>
</c:if>
</html>