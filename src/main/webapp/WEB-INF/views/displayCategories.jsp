<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminCategoryPage.css' />">
</head>
<body>
	<div class="title">
		<h2>Categories</h2>
	</div>
	<form>
		<div class="input-group">
			<input type="text" class="form-control" id="search" name="name"
				placeholder="Enter Category Name to be searched" required>
		 	<button class="btn btn-default" type="submit"
				formaction="/ecommerce/category/searchByName" formmethod="post">
				<i class="fa fa-search"></i>
			</button>
			&nbsp; &nbsp;
			<button type="button" class="btn btn-info btn-sm"
			data-toggle="modal" data-target="#addForm">Add Category</button>
		</div>
	</form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th class="row-header"> ID </th>
				<th class="row-header"> Name </th>
				<th class="row-header"> Actions </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categories}" var="category">
				<form action="category" method="Post">
					<input type="hidden" name="id" value="${category.id}" />
					<input type="hidden" name="categoryId" value="${category.id}"/>
					<tr>
						<td>${category.id}</td>
						<td><button class="btn btn-default" formmethod="post"
								formaction="/ecommerce/category/displayProducts">
								${category.name}</button></td>
						<td>
							<button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#${category.id}">
								<i class="fa  fa-pencil-square-o"></i></button> &nbsp;&nbsp;
							<button type="submit" class="btn btn-danger"
								formaction="/ecommerce/category/delete" formmethod="post">
								<i class="fa fa-trash"></i></button>
						</td>
					</tr>
					<div id="${category.id}" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h3 class="modal-title">Edit Category</h3>
								</div>
								<div class="modal-body">
									<form>
										<input type="text" id="name" name="name" value="${category.name}"
											placeholder="Enter Category Name" pattern="^[A-Za-z- ]+$"
											required /> &nbsp; &nbsp;
										<button type="submit" class="btn btn-warning"
											formaction="/ecommerce/category/update" formmethod="post">Save</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</tbody>
	</table>
	<div class="footer">
		<Strong>**Note:</Strong> Click on the Category name to view Products
	</div>
	<div class="container">
		<div id="addForm" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;
						</button>
						<h3 class="modal-title">Add New Category</h3>
					</div>
					<div class="modal-body">
						<form>
							<input type="text" name="name" value="${category.name}"
								placeholder="Enter Category Name" id="categoryName" 
								pattern="^[A-Za-z- ]+$"
								required /> &nbsp;&nbsp;
							<button type="submit" class="btn btn-info" formmethod="post"
								formaction="/ecommerce/category/insert"> Save </button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<c:if test="${null != message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</html>