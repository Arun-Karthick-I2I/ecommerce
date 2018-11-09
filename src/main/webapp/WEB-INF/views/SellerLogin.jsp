<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Ecommerce</title>
	<link rel="stylesheet" href="<c:url value='/resources/css/seller_login.css' />" >
</head>

<body>
	<div class="lowin">
		<div class="lowin-brand">
			<img src="<c:url value='/resources/images/seller_truck.png' />" alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					<form method="POST" action="/ecommerce/login">
						<p>Sign in as Seller</p>
						<div class="lowin-group">
							<label>Mobile Number</label>
							<input type="text" name="userName" pattern="[6-9]{1}[0-9]{9}" maxlength="10" class="lowin-input">
						</div>
						<div class="lowin-group password-group">
							<label>Password</label>
							<input type="password" name="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,32}" maxlength="32" class="lowin-input">
						</div>
						<input type="hidden" name="role" value="SELLER" />
						<button type="submit" class="lowin-btn login-btn"> Sign In </button>

						<div class="text-foot">
							Don't have an account? <a href="" class="register-link">Register</a>
						</div>
					</form>
				</div>
			</div>

			<div class="lowin-box lowin-register">
				<div class="lowin-box-inner">
					<form method="POST" action="/ecommerce/registerSeller">
						<p>Let's create your Seller Account</p>
						<div class="lowin-group">
							<label>Name</label>
							<input type="text" name="name" pattern="[a-zA-Z]{1,30}" maxlength="30" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Mobile Number</label>
							<input type="text" name="user.userName" pattern="[6-9]{1}[0-9]{9}" maxlength="10" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Email</label>
							<input type="email" name="emailId"  maxlength="50" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Password</label>
							<input type="password" name="user.password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,32}" title="Password should contain the following:Uppercase,Lowercase Letters and Numbers" maxlength="32" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>Confirm Password</label>
							<input type="password" name="confirmPassword" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{8,32}" title="Password should contain the following:Uppercase,Lowercase Letters and Numbers" maxlength="32" class="lowin-input">
						</div>
						<input type="hidden" name="user.role" value="SELLER" />
						<button type="submit" class="lowin-btn"> Sign Up </button>

						<div class="text-foot">
							Already have an account? <a href="" class="login-link">Login</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/seller_login.js' />"></script>
	<script>
		Auth.init({
			login_url: '/ecommerce/login',
		});
	</script>
</body>
</html>
