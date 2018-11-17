<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/AddressForm.css' />" />
</head>
<body>
	<jsp:include page="SellerHeader.jsp"></jsp:include>
	<form id="AddressForm" method="POST">
		<div class="w-25 p-3 mx-auto">
			<c:if test="${null == address.id}">
				<div class="p-3 font-weight-bold">Add Address</div>
			</c:if>
			<c:if test="${null != address.id}">
				<div class="p-3 font-weight-bold">Update Address</div>
				<input type="hidden" name="id" value="${address.id}" />
			</c:if>
			<div class="form-group">
				<label for="addressLine">Address Line:</label> <input type="text"
					class="form-control" name="addressLine" maxlength="150"
					value="${address.addressLine}" id="addressLine" required>
			</div>
			<div class="form-group">
				<label class="city">City:</label><input type="text"
					class="form-control" pattern="[a-zA-Z]{1}[a-zA-Z ]{1,29}"
					maxlength="30" title="City name should contain only alphabets"
					name="city" value="${address.city}" id="city" required>
			</div>
			<div class="form-group">
				<label class="state">State:</label><input type="text"
					class="form-control" pattern="[a-zA-Z]{1}[a-zA-Z ]{1,29}"
					maxlength="30" title="State name should contain only alphabets"
					name="state" value="${address.state}" id="state" required>
			</div>
			<div class="form-group">
				<label class="country">Country:</label><input type="text"
					class="form-control" pattern="[a-zA-Z]{1}[a-zA-Z ]{1,29}"
					maxlength="30" title="Country name should contain only alphabets"
					name="country" value="${address.country}" id="country" required>
			</div>
			<div class="form-group">
				<label class="pincode">Pincode:</label><input type="text"
					pattern="[0-9]{6}" maxlength="6"
					title="Pincode should contain 6 digits" class="form-control"
					name="pincode" value="${address.pincode}" id="pincode" required>
			</div>
			<c:if test="${null != address.id}">
				<button type="submit" class="btn btn-outline-primary"
					formaction="/ecommerce/updateAddress">Update Address</button>
			</c:if>
			<c:if test="${null == address.id}">
				<button type="submit" class="btn btn-outline-primary"
					formaction="/ecommerce/addAddress">Add Address</button>
				<button type="reset" class="btn btn-outline-danger">Reset</button>
			</c:if>
			<button type="submit" class="btn btn-outline-danger"
				form="backtoHome">Cancel</button>
		</div>
	</form>
	<form id="backtoHome" action="/ecommerce/seller/home" method="GET"></form>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>