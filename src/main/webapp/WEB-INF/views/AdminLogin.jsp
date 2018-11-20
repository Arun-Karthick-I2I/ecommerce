<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
    	<link rel = "icon" type = "image/png" 
    		href = "<c:url value='/resources/images/ecommerce-logo-1-dribbble.png'/> ">
    	<link rel="stylesheet" 
			href="<c:url value='/resources/css/bootstrap.css' />"/>
    	<link rel="stylesheet" 
    		href = "<c:url value='/resources/css/dancing-script.css'/> ">
        <link rel="stylesheet" type="text/css" 
        	href= "<c:url value='/resources/css/AdminLogin.css'/>" />
        <link rel="stylesheet"
			href="<c:url value='/resources/css/AdminSnackbar.css' />">
    </head>
    <body class="grid">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="wrap">
                    <p class="form-title">
                        Admin &nbsp;&nbsp; Login</p>
                    <form class="login">
                    	<input type="hidden" name="role" value="ADMIN"/>
                    	<input type="text" name="userName" placeholder="Mobile Number"
                 	    	pattern = "[6-9][0-9]{9}" required/>
                    	<input type="password" name="password" placeholder="Password" 
                    		id="password" data-toggle="password"
                        	required/>
                		<input type="submit" value="Login" formaction = "/ecommerce/login" 
                    		formmethod = "post"/>
               		</form>
            </div>
        </div>
    </div>
	</div>
</body>
	<script src="<c:url value='/resources/js/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap-show-password.min.js' />"></script>
	<script src="<c:url value='/resources/js/Admin.js' />"></script>
	<script type="text/javascript">
		$("#password").password('data-toggle');
	</script>
	<c:if test="${null != message}">
		<div id="snackbar">${message}</div>
		<script>
			showSnackBar();
		</script>
	</c:if>
</html>
