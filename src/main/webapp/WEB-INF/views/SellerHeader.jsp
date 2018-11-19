<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/all.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerHeader.css' />" />
<nav class="navbar navbar-expand-sm navbar-custom navbar-dark fixed-top">
	<a class="navbar-brand font-weight-bold" href="/ecommerce/seller/home">Ecommerce</a>
	<ul class="navbar-nav">
		<li class="nav-item">
			<div class="dropdown">
				<button class="btn btn-navbar-custom dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="fas fa-warehouse" style="font-size: 32px; color: white"></i>
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="/ecommerce/seller/newProduct">Add
						to Warehouse</a> <a class="dropdown-item"
						href="/ecommerce/seller/home">Show Warehouse</a>
				</div>
			</div>
		</li>
		<li class="nav-item">
			<div class="dropdown">
				<button class="btn btn-navbar-custom dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="fas fa-shipping-fast"
						style="font-size: 32px; color: white"></i>
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item"
						href="/ecommerce/seller/getOrders?status=ORDERED">New Orders</a> <a
						class="dropdown-item"
						href="/ecommerce/seller/getOrders?status=DISPATCHED">Dispatched
						Orders</a> <a class="dropdown-item"
						href="/ecommerce/seller/getOrders?status=DELIVERED">Delivered
						Orders</a> <a class="dropdown-item"
						href="/ecommerce/seller/getOrders?status=CANCELLED">Cancelled
						Orders</a> <a class="dropdown-item"
						href="/ecommerce/seller/getOrders?status=RETURNED">Returned
						Orders</a> <a class="dropdown-item"
						href="/ecommerce/seller/getAllOrders">All Orders</a>
				</div>
			</div>
		</li>
		<li class="nav-item">
			<div class="dropdown">
				<button class="btn btn-navbar-custom dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="fas fa-map-marked-alt"
						style="font-size: 32px; color: white"></i>
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="/ecommerce/newAddress">Add
						Address</a> <a class="dropdown-item" href="/ecommerce/showAddress">Show
						Address</a>
				</div>
				margin-top: 3%;
			</div>
		</li>
	</ul>
	<ul class="navbar-nav ml-auto">
		<li class="nav-item">
			<div class="dropdown">
				<button class="btn btn-navbar-custom dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="fas fa-user-tie" style="font-size: 32px; color: white"></i>
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="/ecommerce/seller/viewProfile">My
						Profile</a>
				</div>
			</div>
		</li>
		<li class="nav-item"><a class="btn btn-logout"
			href="/ecommerce/logout"><i class="fas fa-sign-out-alt"
				style="font-size: 32px; color: white"></i></a></li>
	</ul>
</nav>
