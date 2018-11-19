<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ecommerce</title>
<link rel="icon" type="image/png"
	href="<c:url value='/resources/images/ecommerce-logo-1-dribbble.png' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/myAccount.css' />">
<style>
.form-group {
	width: 50%;
}
</style>
</head>
<body onload="closeAddAddress()">
	<jsp:include page="CustomerHeader.jsp"></jsp:include>
	<div class="myAccount">
		<div class="personalInformation">
			<form class="personalInformation">
				<div class="form-group">
					<label for="name" class="cols-sm-2 control-label">User
						Name&nbsp;&nbsp;<a onclick="openUserNameEdit()" id="userNameEdit">Edit</a>
					</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<input type="hidden" name="id" value=${customer.id } /> <span
								class="input-group-addon"><i class="fa fa-user fa"
								aria-hidden="true"></i></span> <input type="text" class="form-control"
								id="userName" value="${customer.name}" name="name" readonly />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="cols-sm-2 control-label">Email
						Address&nbsp;&nbsp;<a onclick="openEmailIdEdit()" id="emailIdEdit">Edit</a>
					</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-envelope fa" aria-hidden="true"></i></span> <input
								type="text" class="form-control" name="emailId" id="emailId"
								value="${customer.emailId}" readonly />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="mobileNumber" class="cols-sm-2 control-label">Mobile
						Number&nbsp;&nbsp;<a onclick="openMobileNumberEdit()"
						id="mobileNumberEdit">Edit</a>
					</label>
					<div class="cols-sm-10">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-mobile"
								aria-hidden="true"></i></span> <input type="text" class="form-control"
								name="mobileNumber" id="mobileNumber" id="mobileNumber"
								value="${customer.mobileNumber}" readonly />
						</div>
					</div>
				</div>
				<div class="form-group" id="saveCustomer">
					<div class="cols-sm-10">
						<button type="submit" class="btn btn-primary btn-block"
							formaction="/ecommerce/updateCustomer" formmethod="post">Save</button>
						<button type="button" onclick="history.go(0)"
							class="btn btn-default btn-block">Cancel</button>
					</div>
				</div>
			</form>
		</div>
		<div class="address">
			<div class="newAddressButton">
				<button type="button" class="btn btn-default btn-block"
					id="newAddressButton" onclick="openAddAddress()">
					<i class="fa fa-plus" aria-hidden="true"></i> &nbsp;&nbsp;Add New
					Address
				</button>
			</div>
			<div class="createAddress" id="newAddressInProfile">
				<form>
					<div class="cols-sm-10 addressheader">Add New Address</div>
					<br />
					<div class="cols-sm-10 addressLine">
						<div class="input-group">
							<span class="input-group-addon"><b>Address Line</b></span> <input
								type="text" class="form-control" name="addressLine" required />
						</div>
					</div>
					<br /> <br />
					<div class="cols-sm-10 txtSize">
						<div class="input-group">
							<span class="input-group-addon"><b>City&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
							<input type="text" class="form-control" name="city" required />
						</div>
					</div>
					<div class="cols-sm-10 txtSize">
						<div class="input-group">
							<span class="input-group-addon"><b>State&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
							<input type="text" class="form-control" name="state" required />
						</div>
					</div>
					<br /> <br />
					<div class="cols-sm-10 txtSize">
						<div class="input-group">
							<span class="input-group-addon"><b>Country&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
							<input type="text" class="form-control" name="country" required />
						</div>
					</div>
					<div class="cols-sm-10 txtSize">
						<div class="input-group">
							<span class="input-group-addon"><b>Pincode&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
							<input type="text" maxlength="6" pattern="[0-9]{6}"
								title="Pincode should contain 6 digits" class="form-control"
								name="pincode" required />
						</div>
					</div>
					<div class="cols-sm-10 addressLine button">
						<button type="submit" class="btn btn-primary btn-lg"
							formaction="/ecommerce/addAddress" formmethod="post">Save</button>
						<button type="button" class="btn btn-default btn-lg"
							onclick="history.go(0)">Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<br />
		<div class="addressheader">Delivery Addresses</div>
		<div class="existingAddresses">
			<c:forEach var="address" items="${customer.user.addresses}">
				<form>
					<div class="deliveryAddress">
						<i class="fa fa-home" aria-hidden="true"></i>
						&nbsp;&nbsp;${address}
						<div class="addressModifyButton">
							<input type="hidden" name="addressId" value="${address.id}">
							<button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#${address.id}"
								onclick="closeAddAddress()">Edit</button>
							<button type="submit" class="btn btn-danger btn-sm"
								formaction="/ecommerce/removeAddress"
								formmethod="post">Delete</button>
						</div>
					</div>
				</form>
				<br />
				<div class="modal fade" id="${address.id}" role="dialog">
					<div class="modal-dialog modal-md">
						<div class="modal-content">
							<form>
								<div class="modal-header">
									<h4>Edit Address</h4>
								</div>
								<div class="modal-body">
									<div class="editAddress">
										<input type="hidden" name="id" value="${address.id}">
										<div class="cols-sm-10 addressLine">
											<div class="input-group">
												<span class="input-group-addon"><b>Address Line</b></span> <input
													type="text" class="form-control" name="addressLine"
													value="${address.addressLine}" required />
											</div>
										</div>
										<br />
										<div class="cols-sm-10 txtSize">
											<div class="input-group">
												<span class="input-group-addon"><b>City&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
												<input type="text" class="form-control" name="city"
													value="${address.city}" required />
											</div>
										</div>
										<br />

										<div class="cols-sm-10 txtSize right">
											<div class="input-group">
												<span class="input-group-addon"><b>State&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
												<input type="text" class="form-control" name="state"
													value="${address.state}" required />
											</div>
										</div>
										<br />
										<div class="cols-sm-10 txtSize">
											<div class="input-group">
												<span class="input-group-addon"><b>Country&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
												<input type="text" class="form-control" name="country"
													value="${address.country}" required />
											</div>
										</div>
										<br />

										<div class="cols-sm-10 txtSize right">
											<div class="input-group">
												<span class="input-group-addon"><b>Pincode&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
												<input type="text" class="form-control" maxlength="6"
													name="pincode" value="${address.pincode}" required />
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary btn-md"
										formmethod="post" formaction="/ecommerce/updateAddress">Save</button>
									<button type="button" class="btn btn-danger btn-md"
										data-dismiss="modal">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="CustomerFooter.jsp"></jsp:include>
</body>

<script>
	function openAddAddress(event) {
		$("#newAddressInProfile").show();
		$("#newAddressButton").hide();
	}
	function closeAddAddress(event) {
		$("#newAddressInProfile").hide();
		$("#newAddressButton").show();
		$("#saveCustomer").hide();
	}
	function openUserNameEdit(event) {
		document.getElementById("userName").removeAttribute('readonly');
		$("#userNameEdit").hide();
		$("#saveCustomer").show();
	}
	function openEmailIdEdit(event) {
		document.getElementById("emailId").removeAttribute('readonly');
		$("#emailIdEdit").hide();
		$("#saveCustomer").show();
	}
	function openMobileNumberEdit(event) {
		document.getElementById("mobileNumber").removeAttribute('readonly');
		$("#mobileNumberEdit").hide();
		$("#saveCustomer").show();
	}
</script>











</html>