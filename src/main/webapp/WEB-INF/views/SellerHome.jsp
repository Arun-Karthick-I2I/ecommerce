<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerHome.css' />" />
</head>
<body>
	<button type="button" class="btn btn-info" data-toggle="modal"
		data-target="#modalBox">Add Product</button>
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
								id="name" pattern="[a-zA-Z][a-zA-Z0-9 ]{1,150}"
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
									value="${product.name}" pattern="[a-zA-Z][a-zA-Z0-9 ]{1,150}"
									title="Product Name can contain Alphabets, Numbers and Spaces Eg.Sony Xperia Z3(Pearl White, 64GB)"
									readonly required>
							</div>
							<div class="form-group">
								<label for="description">Product Description</label>
								<textarea rows="5" cols="50" name="description" id="description" required></textarea>
							</div>
							<div class="form-group">
								<label for="image">Upload Product Image (Supported
									Format : .jpg, .png, gif)</label> <input type="file"
									class="form-control-file" name="productImage" id="image"
									accept=".jpg, .png, .gif" required>
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
	<c:if test="${selectProduct}">
		<div class="modal fade" id="productModal">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header bg-warning text-center">
						<h4 class="modal-title text-white w-100 font-weight-bold ">Products
							Available in that Name</h4>
						<button type="button" class="close text-white"
							data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<c:forEach var="product" items="${products}">
							<form action="/ecommerce/seller/showWarehouseProductForm"
								method="POST">
								<div class="row">
									<div class="col-sm-8">
										<input hidden name="productId" value="${product.id}" />${product.name}</div>
									<div class="col-sm-4">
										<button type=submit class="btn btn-primary btn-sm">Select</button>
									</div>
								</div>
							</form>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
	<script>
		showProductForm()
	</script>
</body>
</html>
