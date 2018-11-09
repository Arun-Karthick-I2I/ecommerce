<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Ecommerce</title>
	<link rel="stylesheet" href="<c:url value='/resources/seller_login.css' />" >
</head>

<body>
	<div class="lowin">
		<div class="lowin-brand">
			<img src="<c:url value='/resources/kodinger.jpg' />" alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					<form method="POST" action="login">
						<p>Sign in to continue</p>
						<div class="lowin-group">
							<label>Mobile Number</label>
							<input type="text" name="userName" class="lowin-input">
						</div>
						<div class="lowin-group password-group">
							<label>Password</label>
							<input type="password" name="password" class="lowin-input">
						</div>
						<button class="lowin-btn login-btn">
							Sign In
						</button>

						<div class="text-foot">
							Don't have an account? <a href="" class="register-link">Register</a>
						</div>
					</form>
				</div>
			</div>

			<div class="lowin-box lowin-register">
				<div class="lowin-box-inner">
					<form>
						<p>Let's create your account</p>
						<div class="lowin-group">
							<label>Name</label>
							<input type="text" name="name" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Email</label>
							<input type="email" name="email" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Password</label>
							<input type="password" name="password" class="lowin-input">
						</div>
						<button class="lowin-btn">
							Sign Up
						</button>

						<div class="text-foot">
							Already have an account? <a href="" class="login-link">Login</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	
		<footer class="lowin-footer"></footer>
	</div>

	<script src="<c:url value='/resources/seller_login.js' />"></script>
</body>
</html>
