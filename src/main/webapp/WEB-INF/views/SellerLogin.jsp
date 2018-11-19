<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Ecommerce</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/seller_login.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/SellerSnackbar.css' />" />
</head>

<body>
	<div class="lowin">
		<div class="lowin-brand">
			<img src="<c:url value='/resources/images/seller_truck.png' />"
				alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					<form method="POST" action="/ecommerce/login">
						<p>Sign in as Seller</p>
						<div class="form-group">
							<input type="text" name="userName"
								placeholder="Enter Mobile Number" pattern="[6-9]{1}[0-9]{9}"
								title="Mobile Number should contain 10 digits and should start with any of the following numbers 6,7,8,9"
								maxlength="10" class="lowin-input" required>
						</div>
						<div class="form-group password-group">
							<input type="password" name="password"
								placeholder="Enter Password"
								pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
								title="Password should contain atleast 8 characters (1 Uppercase,1 LowerCase, 1 Number/Special Character)"
								maxlength="32" class="lowin-input" required>
						</div>
						<input type="hidden" name="role" value="SELLER" />
						<button type="submit" class="lowin-btn login-btn">Sign In
						</button>

						<div class="text-foot">
							Don't have an account? <a href="" class="register-link">Register</a>
						</div>
					</form>
				</div>
			</div>

			<div class="lowin-box lowin-register">
				<div class="lowin-box-inner">
					<form method="POST" action="/ecommerce/registerSeller">
						<p>Let's Create your Seller Account</p>
						<div class="form-group">
							<input type="text" name="name" placeholder="Enter Name"
								pattern="[a-zA-Z]{1,30}"
								title="Name should contain only Alphabets" maxlength="30"
								class="lowin-input" required>
						</div>
						<div class="form-group">
							<input type="email" name="emailId" placeholder="Enter Email ID"
								maxlength="50" class="lowin-input" required>
						</div>
						<div class="form-group">
							<input type="text" name="user.userName"
								placeholder="Enter Mobile Number" pattern="[6-9]{1}[0-9]{9}"
								title="Mobile Number should contain 10 digits and should start with any of the following numbers 6,7,8,9"
								maxlength="10" class="lowin-input" required>
						</div>
						<div class="form-group">
							<input type="password" id="reg_pass" name="user.password"
								placeholder="Enter Password"
								pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
								title="Password should contain atleast 8 characters (1 Uppercase,1 LowerCase, 1 Number/Special Character)"
								maxlength="32" class="lowin-input" required>
						</div>
						<div class="form-group">
							<input type="password" id="reg_confirm_pass"
								name="confirmPassword" placeholder="Re-Enter Password"
								pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
								title="Password should contain atleast 8 characters (1 Uppercase,1 LowerCase, 1 Number/Special Character)"
								maxlength="32" class="lowin-input" required>
						</div>
						<input type="hidden" name="user.role" value="SELLER" />
						<button type="submit" class="lowin-btn">Sign Up</button>

						<div class="text-foot">
							Already have an account? <a href="" class="login-link">Login</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${null != message}">
		<div id="snackbar">${message}</div>
	</c:if>
	<script src="<c:url value='/resources/js/seller_login.js' />"></script>
	<script src="<c:url value='/resources/js/Seller.js' />"></script>
	<c:if test="${null != message}">
		<script>
		showSnackBar();
		</script>
		<c:remove var="message" />
	</c:if>
	<script>
		Auth.init({
			login_url : '/ecommerce/login',
		});
	</script>
</body>
</html>
