<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category</title>
</head>
<body>
	<form>
	<input type="text" name="name" value = "${category.name}" 
        pattern = "^[A-Za-z- ]+$" required/>>
    <button type = "submit" formaction = "/ecommerce/category/insert"
    	formmethod="post">
    	Save </button>
	</form>
</body>
</html>