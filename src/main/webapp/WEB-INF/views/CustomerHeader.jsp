<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
									<option name="id" value=1>Saaadw</option>
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
					<li><a href="#">Anantharaj &nbsp;<span
							class="glyphicon glyphicon-chevron-down"></span></a></li>
					<li>
						<button type="button" class="btn btn-default" id="btnloginModal"
							data-toggle="modal" data-target="#Customerlogin">
							<span class="glyphicon glyphicon-user"></span> Login & SignUp
						</button>

						<div class="modal fade" id="Customerlogin" role="dialog">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4>Welcome to pandaZone</h4>
									</div>
									<div class="modal-body">
										<form action="/examples/actions/confirmation.php"
											method="post">
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon">
													    <i class="fa fa-user"></i>
													</span> 
													<input type="text" class="form-control" name="username"
														placeholder="Username" required="required">
												</div>
											</div>
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="fa fa-lock"></i></span> <input type="text"
														class="form-control" name="password"
														placeholder="Password" required="required">
												</div>
											</div>
											<div class="form-group">
												<button type="submit"
													class="btn btn-primary btn-block btn-lg">Sign In</button>
											</div>
										</form>
									</div>
							       <div class="modal-footer">
										New to PandaZone? &nbsp;<a href=""><Strong>SignUp</Strong></a>
									</div>
								</div>
								</div>
							</div>
							
							
					</li>
					<li><a href="#"> <span
							class="glyphicon glyphicon-shopping-cart"></span> Cart
					</a></li>
				</ul>
			</div>
		</nav>
	</div>
	<br /></br>




</body>
</html>

