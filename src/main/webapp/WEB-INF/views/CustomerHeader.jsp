<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>PandaZone</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/CustomerHeader.js"/>">
	
</script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/CustomerHeader.css' />">

</head>
<body>

	<div>
		<nav class="navbar navbar-fixed-top navbar-custom">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">pandaZone</a>
				</div>
				<ul class="nav navbar-nav">
					<form class="navbar-form navbar-left" action="">
						<div class="input-group col-lg-12">

							<div class="input-group-btn">
								<select class="form-control">
									<option name="id" value=0 selected>All Categories</option>
									<c:forEach var="category" items="${categories}">
										<option name="id" value=${category.id} }>${category.name}</option>
									</c:forEach>
									<option name="id" value=2>Opeldssssssss</option>
									<option name="id" value=3>Audi</option>
								</select>
							</div>
							<input type="text" class="form-control" id="search"
								placeholder="Search for product, brand and more">
							<div class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</form>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="">Anantharaj &nbsp;<span
							class="glyphicon glyphicon-chevron-down"></span></a></li>
					<li>
						<button type="button" class="btn btn-default" id="btnloginModal"
							onclick="openLoginModal()">
							<span class="glyphicon glyphicon-user"></span> Login & SignUp
						</button>
					</li>
					<li><a href=""> <span
							class="glyphicon glyphicon-shopping-cart"></span> Cart
					</a></li>
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
							alt="pandaZone" />
						<h4>Welcome to pandaZone</h4>
					</div>
				</div>
				<div class="modal-body">
					<form action="/ecommerce/login" method="post">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"> <i class="fa fa-user"></i>
								</span> <input type="text" class="form-control" name="userName"
									placeholder="Mobile Number" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="text" class="form-control" name="password"
									placeholder="Password" required="required">
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block btn-lg">
								SignIn</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					New to PandaZone? &nbsp;<a onclick="openSignUpModal()"><Strong>SignUp</Strong></a>
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
							alt="pandaZone" />
						<h4>Welcome to pandaZone</h4>
					</div>
				</div>
				<div class="modal-body">
					<form action="" method="post">
						<div class="form-group">
							<div class="input-group">
								<input type="hidden" class="form-control" name="role"
									value="CUSTOMER" />
							    <span class="input-group-addon">
									<i class="fa fa-user"></i>
								</span> <input type="text" class="form-control" name="name"
									placeholder="Name" required="required"> </span>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"> <i class="fa fa-mobile"></i>
								</span> <input type="text" class="form-control" name="mobileNumber"
									placeholder="Mobile Number" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="fa fa-envelope"></i>
								</span> <input type="text" class="form-control" name="emailId"
									placeholder="Email" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control" name="user.password"
									placeholder="Password" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" class="form-control"
									name="confirmPassword" placeholder="Confirm Password"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block btn-lg">
								SignUp</button>
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

<script type="text/javascript">
	function openLoginModal(event) {
		$("#Customerlogin").modal("show");
		$("#CustomerSignUp").modal("hide");
	}

	function openSignUpModal(event) {
		$("#Customerlogin").modal("hide");
		$("#CustomerSignUp").modal("show");
	}
</script>


</html>

