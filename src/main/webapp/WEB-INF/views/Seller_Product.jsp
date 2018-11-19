<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerHome.css' />" />
</head>
<body>
	<jsp:include page="SellerHeader.jsp"></jsp:include>
	<div class="buttonWrapper">
		<div class="addbox">
		<button class="btn btn-warning" type="button" data-toggle="modal"
			data-target="#modalBox">Add Something New</button>
		</div>
		<div class="searchbox">
		<form action="/ecommerce/seller/searchProduct" method="GET" >
  			<input class="search" type="text" name="productName" placeholder="Search.." required>
		</form>
		</div>
	</div>
	<div class="container">
		<c:if test="${0 == products.size()}" >
			<div>
			<img alt="No Product Found" src="<c:url value='/resources/images/noproduct.png' />" height="300px" width="300px"></img>
			</div>
		</c:if>
		<c:forEach var="product" items="${products}">
			<div class="card">
				<div class="flip-box">
					<div class="flip-box-inner">
						<div class="flip-box-front">
							<img class="card-img-top"
								src="data:image/jpg;base64,${product.base64Image}">
							<h5 class="card-title">${product.name}</h5>
						</div>
						<div class="flip-box-back">
							<div class="card-body">
								<h5 class="card-title">${product.name}</h5>
								<p class="card-text">Want to Sell ?</p>
								<form action="/ecommerce/seller/searchProduct" method="GET">
									<input type="hidden" name="productName" value="${product.name}" />
									<button class="btn btn-sm btn-info" type="submit">Add
										to Warehouse</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="modal fade" id="modalBox">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header bg-warning text-center">
					<h4 class="modal-title text-white w-100 font-weight-bold">Enter
						Product Name</h4>
					<button type="button" class="close text-white" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form action="/ecommerce/seller/searchProduct" method="GET">
						<div class="form-group">
							<input type="text" class="form-control" name="productName"
								id="name" pattern="[a-zA-Z][a-zA-Z0-9 ()-,.':]{1,150}"
								placeholder="Product Name"
								title="Product Name can contain Alphabets, Numbers and Spaces Eg.Sony Xperia Z3(Pearl White, 64GB)"
								required>
						</div>
						<button type="submit" class="btn btn-primary btn-right">Proceed</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${newProduct}">
		<div class="modal fade" id="productModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header bg-warning text-center">
						<h4 class="modal-title text-white w-100 font-weight-bold">New
							Product Form</h4>
						<button type="button" class="close text-white"
							data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form action="/ecommerce/seller/createProduct" method="POST"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="name">Product Name</label> <input type="text"
									class="form-control" name="name" id="name"
									value="${product.name}" readonly required>
							</div>
							<div class="form-group">
								<label for="description">Product Description</label>
								<textarea class="form-control" cols="50" name="description"
									id="description" required></textarea>
							</div>
							<div class="form-group">
								<label for="image">Upload Product Image (Supported
									Format : .jpg, .png)</label> <input type="file"
									class="form-control-file" name="productImage" id="image"
									accept=".jpg, .jpeg, .png" required>
							</div>
							<div class="form-group">
								<label for="category">Category</label> <select
									class="form-control" name="category.id" id="category">
									<c:forEach var="category" items="${categories}">
										<option value="${category.id}">${category.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group-btn">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
	<script>
		showProductForm();
	</script>
</body>
</html>

