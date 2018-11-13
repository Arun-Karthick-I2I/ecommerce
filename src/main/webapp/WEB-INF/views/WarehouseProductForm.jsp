<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Ecommerce</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/bootstrap.min.css' />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/WarehouseProductForm.css' />" />
</head>
<body>
	<div class="w-100 p-3 mx-auto">
		<form id="warehouseProductForm" method="POST">
			<c:if test="${null == warehouseProduct.id}">
				<div class="p-3 font-weight-bold">Add Product to your
					Warehouse</div>
			</c:if>
			<c:if test="${null != warehouseProduct.id}">
				<div class="p-3 font-weight-bold">Update Product in your
					Warehouse</div>
			</c:if>
			<div class="w-25 p-3 product">
				<div class="form-group">
					<input type="hidden" name="product.id" value="${warehouseProduct.product.id}" />
					<input type="hidden" name="seller.id" value="${sellerId}" />
					<label for="name">Product Name</label> <input type="text"
						class="form-control" id="name" name="product.name"
						value="${warehouseProduct.product.name}"
						pattern="[a-zA-Z][a-zA-Z0-9 ]{1,150}"
						title="Product Name can contain Alphabets, Numbers and Spaces Eg.Sony Xperia Z3(Pearl White, 64GB)"
						readonly>
				</div>
				<div class="form-group">
					<label for="description">Product Description</label>
					<textarea class="form-control" rows="5" cols="50" id="description"
						name="product.description" readonly>${warehouseProduct.product.description}</textarea>
				</div>
				<div class="form-group">
					<label for="image"> Product Image </label> <img
						class="form-control"
						src="<c:url value='data:image/jpg;base64,${warehouseProduct.product.base64Image}'/>" />
				</div>
				<div class="form-group">
					<label for="category">Category</label> <input type="text"
						class="form-control" id="category" name="product.category.name"
						value="${warehouseProduct.product.category.name}" readonly>
				</div>
			</div>
			<div class="w-25 p-3 warehouse">
				<div class="form-group">
					<label for="quantity">Quantity:</label> <input type="number"
						class="form-control" name="quantity" id="quantity">
				</div>
				<div class="form-group">
					<label class="price">Price:</label><input type="number"
						class="form-control" name="price" id="price">
				</div>
				<c:if test="${null != warehouseProduct.id}">
					<button type="submit" class="btn btn-outline-primary"
						formaction="/ecommerce/seller/updateWarehouseProduct">Update Warehouse</button>
				</c:if>
				<c:if test="${null == warehouseProduct.id}">
					<button type="submit" class="btn btn-outline-primary"
						formaction="/ecommerce/seller/addWarehouseProduct">Add to Warehouse</button>
					<button type="reset" class="btn btn-outline-danger">Reset</button>
				</c:if>
				<button type="submit" class="btn btn-outline-danger"
					form="backtoHome">Cancel</button>
			</div>
	</form>
		<form id="backtoHome" action="/ecommerce/seller/" method="GET"></form>
	</div>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
</body>
</html>