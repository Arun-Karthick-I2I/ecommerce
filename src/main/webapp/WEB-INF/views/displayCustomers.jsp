<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Customers</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminCustomerPage.css' />">
</head>
<body>
	<div class="title">
		<h2> Customers </h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="name"
				placeholder="Enter Customer Name to be searched" required>
		 	<button class="btn btn-default" type="submit"
				formaction="/ecommerce/admin/searchByCustomerName" formmethod="post">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th class="row-header"> ID </th>
				<th class="row-header"> Name </th>
				<th class="row-header"> Mobile Number </th>
				<th class="row-header"> Email ID </th>
				<th></th>
			</tr>
		</thead>
		<tbody id="myTable">
			<c:forEach items="${customers}" var="customer">
				<form action="admin" method="Post">
					<input type="hidden" name="id" value="${customer.id}" />
					<tr>
						<td>${customer.id}</td>
						<td><button class="btn btn-default" formmethod="post"
								formaction="/ecommerce/admin/displayCustomerOrders">
								${customer.name}</button></td>
						<td>${customer.mobileNumber}</td>
						<td>${customer.emailId}</td>
						<td>
							<button type="submit" class="btn btn-danger"
								formaction="/ecommerce/admin/deleteCustomer" formmethod="post">
								<i class="fa fa-trash"></i></button>
						</td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
	</table>
	<div class="footer">
		<Strong>**Note:</Strong> Click on the Customer name to view the Orders placed by the Customer
	</div>
</body>
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>

<script>
$(document).ready(function(){
  $("#search").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
<c:if test="${null != message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</html>