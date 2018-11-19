<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>Ecommerce</title>
<link rel = "icon" type = "image/png" href = "<c:url value='/resources/images/ecommerce-logo-1-dribbble.png' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.css' />"/>
<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css' />"/>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminHeader.css' />">
</head>
<body>
	<div>
		<div id="mySidebar" class="sidebar">
			<form>
  			<p><a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a></p>
  			<p><button class="btn default" formaction="/ecommerce/category/display" 
  				formmethod="get">Categories</button></p>
  			<p><button class="btn default" formaction="/ecommerce/product/display"
  				formmethod="get">Products</button></p>
  			<p><button class="btn default" formaction="/ecommerce/admin/displayCustomers"
  				formmethod="get">Customers</button></p>
  			<p><button class="btn default" formaction="/ecommerce/admin/displaySellers"
  				formmethod="get">Sellers</button></p>
  			<p><button class="btn default" formaction="/ecommerce/admin/displayOrders"
  				formmethod="get">Orders</button></p>
  			</form>
		</div>

		<nav class="navbar navbar-custom">
			<div class="container-fluid">
				<div class="navbar-header">
					<ul class="nav navbar-nav navbar-left">
					    <li><a class="openbtn" id="main" onclick="openNav()">
					    <i class="fa fa-bars"></i></a> </li>
 						
 				        <li>
 				        	<form class = "home" method="get" 
 				        		action="/ecommerce/category/display">
								<button class="navbar-brand"> 
								Ecommerce </button>
							</form>
					    </li>
					</ul>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><i class="fa fa-user"></i>
					Admin &nbsp;</a></li>
					<li><form class = "logout" action="/ecommerce/logout">
						<button class="btn default"><i class="fa fa-power-off"></i>
					    Logout</button>
					</form></li>
				</ul>
			</div>
		</nav>
	</div>

</body>
	<script src="<c:url value='/resources/js/Admin.js' />"></script>
	<script>
		openNav();
	</script>

	<script>
		closeNav();
	</script>
</html>

