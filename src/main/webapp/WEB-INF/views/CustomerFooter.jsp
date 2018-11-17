<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pandaZone</title>
<style>
.footer {
	position: relative;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: lightgrey;
	height: 40px;
	margin-bottom:0;
}

.footer > .seller {
	margin-left:20%;
	color:black;
	font-size:20px;
	height:100%;
	width:50%;
}


.footer > .seller:hover {
	color:white;
	text-decoration:none;
}

.footer > .copright {
	width:50%;
	margin-left:20%;
	color:black;
	font-size:15px;
	height:100%;
	text-align:right;
}

</style>
</head>
<body>
	<div class="footer">
		<a class="seller" href="/ecommerce/seller/"><i class="fa fa-suitcase"></i> &nbsp;Want to be seller??</a>
		<a class="copright">Copyright&nbsp;<i class="fa fa-copyright"></i>&nbsp;2018-ecommerce.com</a>
	</div>
</body>
</html>