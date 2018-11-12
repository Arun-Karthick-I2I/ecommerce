<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="AdminHeader.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<c:url value='/resources/css/AdminDisplayCategories.css' />">
</head>
<body>
	<div class="title">
		<h2>Category</h2>
	</div>
	<form>
		<div class="form-group">
			<div class="input-group">
				<input type="text" class="form-control" id="search" name="name"
					placeholder="Search for product for Category specified">
				<button class="btn btn-default" type="submit"
					formaction="/ecommerce/category/searchByName" formmethod="post">
					<i class="fa fa-search"></i>
				</button>
				&nbsp; &nbsp;
				<button type="button" class="btn btn-info btn-sm"
					data-toggle="modal" data-target="#addForm">Add Category</button>
			</div>
		</div>
	</form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th class="row-header">ID</th>
				<th class="row-header">Name</th>
				<th colspan=2></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categories}" var="category">
				<form action="category" method="Post">
					<input type="hidden" name="id" value="${category.id}" />
					<tr>
						<td>${category.id}</td>
						<td><button class="btn default" formmethod="post"
								formaction="/ecommerce/category/displayProducts">
								${category.name}</button></td>
						<td>
							<button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#${category.id}">edit
							</button> &nbsp;&nbsp;
							<button type="submit" class="btn danger"
								formaction="/ecommerce/category/delete" formmethod="post">
								Delete</button>
						</td>
					</tr>
					<div id="${category.id}" class="modal fade" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Edit Category</h4>
								</div>
								<div class="modal-body">
									<form>
										<input type="text" name="name" value="${category.name}"
											placeholder="Enter Category Name" pattern="^[A-Za-z- ]+$"
											required /><br />
										<button type="submit" class="btn btn-info"
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
	<div class="container">
		<div id="addForm" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Add New Category</h4>
					</div>
					<div class="modal-body">
						<form>
							<input type="text" name="name" value="${category.name}"
								placeholder="Enter Category Name" pattern="^[A-Za-z- ]+$"
								required /><br />

							<button type="submit" class="btn btn-info"
								formaction="/ecommerce/category/insert" formmethod="post">Save</button>
						</form>
					</div>

				</div>

			</div>
		</div>
	</div>
</body>
<c:if test="${null != message}">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</html>