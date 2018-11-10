<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
</head>
<body>
<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modalBox">Add Product</button>
	<div class="modal" id="modalBox">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
		        <div class="modal-header">
		          <h4 class="modal-title">Add New Product</h4>
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
				<div class="modal-body">
					<form action="/ecommerce/seller/createProduct" method="POST">
						<div class="form-group">
							<label for="name">Product Name</label> <input type="text"
								class="form-control" name="name" id="name"
								pattern="[a-zA-Z][a-zA-Z0-9 ]{1,150}"
								title="Product Name can contain Alphabets, Numbers and Spaces Eg.Sony Xperia Z3(Pearl White, 64GB)"
								required>
						</div>
						<div class="form-group">
							<label for="description">Product Description</label> <input
								type="text" class="form-control" name="description"
								id="description" pattern="[a-zA-Z][a-zA-Z0-9 ]{1,250}"
								title="Product Name can contain Alphabets, Numbers and Spaces"
								required>
						</div>
						<div class="form-group">
							<label for="image">Upload Product Image (Supported Format
								: .jpg, .png, gif)</label> <input type="file" class="form-control-file"
								name="image" id="image" accept=".jpg, .png, .gif" required>
						</div>
						<div class="form-group">
							<label for="category">Category</label> <select
								class="form-control" name="category.id" id="category">
								<c:forEach var="category" items="${categories}">
									<option value="${category.id}">${category.name}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>
