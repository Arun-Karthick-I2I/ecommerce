<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <link href= "<c:url value='/resources/css/adminLogin.css'/>" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="wrap">
                <p class="form-title">
                    Admin Login</p>
                <form class="login">
                <input type="hidden" name="role" value="ADMIN"/>
                <input type="text" placeholder="Username" />
                <input type="password" placeholder="Password" />
                <input type="submit" value="Login" formaction = "/ecommerce/login" formmethod = "post"/>
                </form>
            </div>
        </div>
    </div>
</div>
