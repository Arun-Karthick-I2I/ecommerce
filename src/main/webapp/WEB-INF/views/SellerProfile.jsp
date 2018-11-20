<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="icon" type="image/png"
	href="<c:url value='/resources/images/ecommerce-logo-1-dribbble.png'/> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerProfile.css' />" />
</head>
<body>
	<jsp:include page="SellerHeader.jsp"></jsp:include>
	<c:if test="${viewProfile}">
		<form id="sellerForm" method="POST">
			<div class="w-25 p-3 mx-auto">
				<div id="viewProfile" class="p-3 font-weight-bold">View
					Profile</div>
				<div id="updateProfile" class="p-3 font-weight-bold">Update
					Profile</div>
				<input type="hidden" name="id" value="${seller.id}" />
				<div class="form-group">
					<label for="name">Name:</label> <input type="text"
						class="form-control" pattern="[a-zA-Z]{1}[a-zA-Z ]{1,49}"
						maxlength="50" name="name" maxlength="150" value="${seller.name}"
						title="Name should contain only alphabets and should not exceed 50 characters"
						id="name" readonly required>
				</div>
				<div class="form-group">
					<label class="mobileNumber">Mobile Number:</label><input
						type="text" class="form-control" pattern="[6,7,8,9]{1}[0-9]{9}"
						maxlength="10" title="Please enter a valid 10 digit mobile number"
						name="mobileNumber" value="${seller.mobileNumber}"
						id="mobileNumber" readonly required>
				</div>
				<div class="form-group">
					<label class="emailId">Email ID:</label><input type="email"
						class="form-control" maxlength="100"
						title="Please Enter a valid Email ID" name="emailId"
						value="${seller.emailId}" id="emailId" readonly required>
				</div>
				<div id="editProfile-btn" class="form-group edit-profile">
					<button type="button" class="btn btn-outline-primary"
						onclick="editProfile()">Edit</button>
				</div>
				<div id="updateProfile-btn" class="form-group update-profile">
					<button type="submit" class="btn btn-outline-primary"
						formaction="/ecommerce/seller/updateProfile">Update Profile</button>
					<button type="submit" class="btn btn-outline-danger"
						form="backtoHome">Cancel</button>
				</div>
			</div>
		</form>
	</c:if>
	<c:if test="${addressForm}">
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
	</c:if>
	<c:if test="${showAddress}">
		<div class="w-75 mx-auto addressWrapper" id="addressWrapper">
			<table class="table table-hover">
				<thead class="thead-light">
					<tr>
						<th scope="col">Address</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="address" items="${addresses}">
						<tr>
							<form method="POST">
								<td>${address}</td>
								<td><input type="hidden" name="addressId"
									value="${address.id}" />
									<button class="btn btn-primary btn-sm" type="submit"
										formaction="/ecommerce/editAddress">Edit</button> <c:if
										test="${1 < addresses.size()}">
										<button class="btn btn-danger btn-sm" type="submit"
											formaction="/ecommerce/removeAddress"
											onclick="return confirm('Are you sure want to delete this address')">Delete</button>
									</c:if></td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<form id="backtoHome" action="/ecommerce/seller/home" method="GET"></form>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
</body>
</html>