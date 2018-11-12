<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pandaZone</title>
<jsp:include page="CustomerHeader.jsp"></jsp:include>


<link rel="stylesheet"
	href="<c:url value='/resources/css/CustomerHome.css' />">

</head>
<body>
<form class="customerHome">

<div class="container">
  <div class="card" style="width:400px">
	<img class="card-img-top zoom" src="<c:url value='/resources/images/Apple MacBook Air Core i5 13.3-inch Laptop (8GB,Silver).jpg' />"
							alt="pandaZone"  />
    <div class="card-body">
      <h4 class="card-title">Anand</h4>
      <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
      <a class="btn btn-primary">See Profile</a>
    </div>
  </div>
</div>

 
</form>






</body>
</html>