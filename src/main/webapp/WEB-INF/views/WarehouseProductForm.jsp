<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Ecommerce</title>
</head>
<body>
	<form id="warehouseProductForm" method="POST">
		<div class="form-group">
			<label for="quantity">Quantity:</label> <input type="number"
				class="form-control" name="quantity" id="quantity">
		</div>
		<div class="form-group">
			<label class="price">Price:</label><input type="number"
				class="form-control" name="price" id="price">
		</div>
	</form>
	<c:if test="${null == warehouseProduct.id}">
		<button type="submit" class="btn btn-primary" form="warehouseProductForm"
			formaction="/ecommerce/seller/updateWarehouseProduct">Update</button>
		<button type="reset" class="btn btn-danger" form="warehouseProductForm">Reset</button>
	</c:if>
	<c:if test="${null != warehouseProduct.id}">
		<button type="submit" class="btn btn-primary" form="warehouseProductForm"
			formaction="/ecommerce/seller/addWarehouseProduct">Create</button>
	</c:if>
	<button type="submit" class="btn btn-info" form="" >Cancel</button>
</body>
</html>