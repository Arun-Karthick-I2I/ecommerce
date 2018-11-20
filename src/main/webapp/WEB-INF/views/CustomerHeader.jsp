<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Ecommerce</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.css' />" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css' />" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/CustomerHeader.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/CustomerSnackbar.css' />">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Dancing+Script" />
</head>
<body>
	<div id="headerBar">
		<nav class="navbar navbar-fixed-top navbar-custom">
			<div class="container-fluid">
				<div class="navbar-header">
					<a href="/ecommerce/" class="navbar-brand">Ecommerce</a>
				</div>

				<ul class="nav navbar-nav">
					<li>
						<form class="navbar-form navbar-left form-group"
							action="/ecommerce/customer/searchProduct" method="POST">
							<div class="input-group col-lg-12">

								<div class="input-group-btn">
									<select name="categoryId" class="form-control">
										<option value="0" selected>All Categories</option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.id}">${category.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="input-group-btn">
									<input type="text" name="name" class="form-control" id="search"
										placeholder="Search for product, brand and more"
										required="required">
								</div>
								<div class="input-group-btn">
									<button class="btn btn-default" type="submit">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${not empty customer}">
						<li><a class="dropdown">${customer.name} &nbsp;<b><i
									class="fa fa-angle-down"></i></b>
								<div class="dropdown-content">
									<form method="get">
										<p>
											<label>
												<button class="btn btn-default"
													formaction="/ecommerce/customer/myaccount">
													<i class="fa fa-user-circle-o"></i> &nbsp; My Profile
												</button>
											</label>
										</p>
										<p>
											<button class="btn btn-default"
												formaction="/ecommerce/customer/myOrders">
												<i class="fa fa-gift"></i> &nbsp; Orders
											</button>
										</p>
										<p>
											<button class="btn btn-default"
												formaction="/ecommerce/logout">
												<i class="fa fa-power-off"></i> &nbsp; LogOut
											</button>
										</p>
									</form>
								</div>
						</a></li>
						<li class="cartButton"><a href="/ecommerce/customer/Cart"><i
								class="fa fa-shopping-cart"></i>&nbsp; Cart</a>
					</c:if>
					<c:if test="${empty customer}">
						<li class="loginbutton">
							<button type="button" class="btn btn-default" id="btnloginModal"
								onclick="openLoginModal()">
								<i class="fa fa-user"></i>&nbsp; Login & SignUp
							</button>
						</li>
					</c:if>
				</ul>
			</div>
		</nav>
	</div>
	<div class="modal fade" id="Customerlogin" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="logo">
						<img src="<c:url value='/resources/images/panda_logo.jpeg' />"
							alt="Ecommerce" />
						<h4>Welcome to Ecommerce</h4>
					</div>
				</div>
				<div class="modal-body">
					<form method="post">
						<div class="form-group">
							<div class="input-group">
								<input type="hidden" class="form-control" name="role"
									value="CUSTOMER" /> <span class="input-group-addon"> <i
									class="fa fa-user"></i>
								</span> <input type="text" class="form-control" name="userName"
									pattern="[6-9]{1}[0-9]{9}" maxlength="10"
									title="Mobile Number should contain 10 digits and should start with any of the following numbers 6,7,8,9"
									placeholder="Mobile Number" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control" name="password"
									pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
									title="Password should contain atleast 8 characters (1 Uppercase,1 LowerCase, 1 Number/Special Character)"
									placeholder="Password" required="required">
							</div>
						</div>
						<div class="form-group">
							<button type="submit" formaction="/ecommerce/login"
								class="btn btn-primary btn-block btn-lg">SignIn</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					New to Ecommerce? &nbsp;<a onclick="openSignUpModal()"><Strong>SignUp</Strong></a>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="CustomerSignUp" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<div class="logo">
						<img class="regPanda"
							src="<c:url value='/resources/images/panda_logo.jpeg' />"
							alt="Ecommerce" />
						<h4>Welcome to Ecommerce</h4>
					</div>
				</div>
				<div class="modal-body">
					<form action="/ecommerce/registerCustomer" method="post" novalidate>
						<div class="personalDetails" id="personelDetails">
							<div class="form-group">
								<div class="input-group">
									<input type="hidden" class="form-control" name="user.role"
										value="CUSTOMER" /> <span class="input-group-addon"><i
										class="fa fa-user"></i></span> <input type="text"
										class="form-control" pattern="[a-zA-Z]{1,30}" maxlength="30"
										title="Name should contain only Alphabets" name="name"
										id="nameModal" placeholder="Name" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-mobile"></i>
									</span> <input type="text" class="form-control" name="user.userName"
										pattern="[6-9]{1}[0-9]{9}" maxlength="10" id="mobileModal"
										title="Mobile Number should contain 10 digits and should start with any of the following numbers 6,7,8,9"
										placeholder="Mobile Number" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-envelope"></i>
									</span> <input type="text" class="form-control" name="emailId"
										id="emailModal" maxlength="50" placeholder="Email" />
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control"
										name="user.password" id="passwordModal"
										pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
										title="Password should contain atleast 8 characters (1 Uppercase,1 LowerCase, 1 Number/Special Character)"
										placeholder="Password" />
								</div>
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-primary btn-block btn-lg"
									onclick="validatePersonalDetails()">
									Continue&nbsp;<i class="fa fa-angle-double-right"></i>
								</button>
							</div>
						</div>
						<div class="address" id="addressDetails">
							<div class="form-group cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"></span> <input type="text"
										class="form-control" id="addressLineModal"
										name="user.addresses[0].addressLine" placeholder="AddressLine" />
								</div>
							</div>
							<div class="form-group cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"></span> <input type="text"
										class="form-control" id="cityModal"
										name="user.addresses[0].city" placeholder="City" />
								</div>
							</div>
							<div class="form-group cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"></span> <input type="text"
										class="form-control" id="stateModal"
										name="user.addresses[0].state" placeholder="State" />
								</div>
							</div>
							<div class="form-group cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"></span> <input type="text"
										class="form-control" id="countryModal"
										name="user.addresses[0].country" placeholder="Country" />
								</div>
							</div>
							<div class="form-group cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"></span> <input type="text"
										class="form-control" maxlength="6" id="pincodeModal"
										name="user.addresses[0].pincode" placeholder="Pincode" />
								</div>
							</div>
							<div class="form-group cols-sm-10 ">
								<button type="submit" class="btn btn-primary btn-block btn-lg"
									onclick="return validateAddressDetails()">SignUp</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					Existing User? &nbsp;<a onclick="openLoginModal()"><Strong>SignIn</Strong></a>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<c:url value='/resources/js/jquery.min.js'/> "></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/CustomerHeader.js"/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/Customer.js' />"></script>

<c:if test="${null != message}">
	<div id="snackbar">${message}</div>
	<script>
		showSnackBar();
	</script>
	<c:remove var="message" />
</c:if>

<script type="text/javascript">
	function isLoggedIn(event) {
		$("#Customerlogin").modal("show");
	}
	function openLoginModal(event) {
		$("#Customerlogin").modal("show");
		$("#CustomerSignUp").modal("hide");
	}

	function openSignUpModal(event) {
		$("#Customerlogin").modal("hide");
		$("#CustomerSignUp").modal("show");
		$("#personelDetails").show();
		$("#addressDetails").hide();
	}

	function validatePersonalDetails(event) {
		if ($("#nameModal").val() == '' || $("#mobileModal").val() == ''
				|| $("#emailModal").val() == ''
				|| $("#passwordModal").val() == '') {
			alert("Fill all the fields");
			return false;
		}
		$("#personelDetails").hide();
		$("#addressDetails").show();
		return true;
	}

	function validateAddressDetails(event) {
		if ($("#addressLineModal").val() == '' || $("#cityModal").val() == ''
				|| $("#stateModal").val() == ''
				|| $("#countryModal").val() == ''
				|| $("#pincodeModal").val() == '') {
			alert("Fill all the fields");
			return false;
		}
		return true;
	}
</script>

</html>
