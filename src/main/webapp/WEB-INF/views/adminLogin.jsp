<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
    	<link rel = "icon" type = "image/png" 
    		href = "<c:url value='/resources/images/ecommerce-logo-1-dribbble.png'/> ">
    	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Dancing+Script" />
        <link rel="stylesheet" type="text/css" 
        	href= "<c:url value='/resources/css/AdminLogin.css'/>" />
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
                        required/>
                	<input type="submit" value="Login" formaction = "/ecommerce/login" 
                    	formmethod = "post"/>
                </form>
            </div>
        </div>
    </div>
	</div>
</body>
<c:if test = "${null != message}">
    <script type="text/javascript">
        alert("${message}");
    </script>
</c:if>
</html>
