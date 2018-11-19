<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Customers</title>
	<link rel="stylesheet"
		href="<c:url value='/resources/css/AdminSellerPage.css' />">
	<link rel="stylesheet"
		href="<c:url value='/resources/css/AdminSnackbar.css' />">
</head>
<body>
	<div class="title">
		<h2> Sellers </h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="name"
				placeholder="Enter Seller Name to be searched" required>
		 	<button class="btn btn-default" type="submit"
				formaction="/ecommerce/admin/searchBySellerName" formmethod="get">
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
				<th class="row-header"> Rating </th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${sellers}" var="seller">
				<form action="admin" method="Post">
					<input type="hidden" name="id" value="${seller.id}" />
					<tr>
						<td>${seller.id}</td>
						<td><button type="button" class="btn btn-default"
								data-toggle="modal" data-target="#${seller.id}">
								${seller.name}</button></td>
						<td>${seller.mobileNumber}</td>
						<td>${seller.emailId}</td>
						<c:if test="${seller.rating ne null}">
							<td>${seller.rating}</td>
						</c:if>
						<c:if test="${seller.rating eq null}">
							<td> Not yet Rated </td>
						</c:if>
						<td>
							<button type="submit" class="btn btn-danger"
								formaction="/ecommerce/admin/deleteSeller" formmethod="post">
								<i class="fa fa-trash"></i></button>
						</td>
					</tr>
					<div id="${seller.id}" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<button type="button" class="close" data-dismiss="modal">
										&times;</button>
									<p> What do you wish to view ???? </p>
									<button class="btn btn-default" formmethod="get"
										formaction="/ecommerce/admin/displaySellerProducts">
										Products </button> or 
									<button class="btn btn-default" formmethod="get"
										formaction="/ecommerce/admin/displaySellerOrders">
										Orders </button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</tbody>
	</table>
	<div class="footer">
		<Strong>**Note:</Strong> Click on the Seller name to view Products sold by the Seller
	</div>
</body>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<script src="<c:url value='/resources/js/Admin.js' />"></script>
	<c:if test="${null != message}">
		<div id="snackbar">${message}</div>
	</c:if>
	<script>
		showSnackBar();
	</script>
</html>