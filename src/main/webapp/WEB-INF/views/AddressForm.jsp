<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
</head>
<body>
	<form id="AddressForm" method="POST">
		<c:if test="${null == address.id}">
			<div class="p-3 font-weight-bold">Add Address</div>
		</c:if>
		<c:if test="${null != address.id}">
			<div class="p-3 font-weight-bold">Update Address</div>
		</c:if>
		<div class="w-25 p-3 warehouse">
			<div class="form-group">
				<label for="addressLine">Address Line:</label> <input type="text"
					class="form-control" name="addressLine" id="addressLine">
			</div>
			<div class="form-group">
				<label class="city">City:</label><input type="text"
					class="form-control" name="city" id="city">
			</div>
			<div class="form-group">
				<label class="state">State:</label><input type="text"
					class="form-control" name="state" id="state">
			</div>
			<div class="form-group">
				<label class="country">Country:</label><input type="text"
					class="form-control" name="country" id="country">
			</div>
			<div class="form-group">
				<label class="pincode">Country:</label><input type="text" pattern="[0-9]{6}" title="Pincode should contain 6 digits"
					class="form-control" name="pincode" id="pincode">
			</div>
			<c:if test="${null != address.id}">
				<button type="submit" class="btn btn-outline-primary"
					formaction="/ecommerce/seller/updateWarehouseProduct">Update Address</button>
			</c:if>
			<c:if test="${null == address.id}">
				<button type="submit" class="btn btn-outline-primary"
					formaction="/ecommerce/seller/addWarehouseProduct">Add Address</button>
				<button type="reset" class="btn btn-outline-danger">Reset</button>
			</c:if>
			<button type="submit" class="btn btn-outline-danger"
				form="backtoHome">Cancel</button>
		</div>
	</form>
	<form id="backtoHome" action="/ecommerce/seller/" method="GET"></form>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>