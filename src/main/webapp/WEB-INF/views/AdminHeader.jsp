<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>Ecommerce</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminHeader.css' />">
</head>
<body>
	<div>
		<div id="mySidebar" class="sidebar">
			<form>
  			<p><a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a></p>
  			<p><button class="btn default" formaction="/ecommerce/category/display" 
  				formmethod="post">Categories</button></p>
  			<p><button class="btn default" formaction="/ecommerce/product/display"
  				formmethod="post">Products</button></p>
  			<p><button class="btn default" formaction="/ecommerce/admin/displayCustomers"
  				formmethod="post">Customers</button></p>
  			<p><button class="btn default" formaction="/ecommerce/admin/displaySellers"
  				formmethod="post">Sellers</button></p>
  			<p><button class="btn default" formaction="/ecommerce/admin/displayOrders"
  				formmethod="post">Orders</button></p>
  			</form>
		</div>

		<nav class="navbar navbar-custom">
			<div class="container-fluid">
				<div class="navbar-header">
					<ul class="nav navbar-nav navbar-left">
					    <li><a class="openbtn" id="main" onclick="openNav()">
					    <i class="fa fa-bars"></i></a> </li>
 						
 				        <li><a class="navbar-brand" href="#">Ecommerce</a><li>
					
					</ul>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href=""><i class="fa fa-user"></i>
					Admin &nbsp;</a></li>
					<li><form class = "logout" action="/ecommerce/logout">
						<button class="btn default"> Logout</button>
					</form></li>
				</ul>
			</div>
		</nav>
	</div>

</body>
<script>
function openNav() {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}
</script>


</html>

