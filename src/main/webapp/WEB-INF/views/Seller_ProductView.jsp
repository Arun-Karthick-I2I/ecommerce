<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<i>${product.name}</i> <br>
	<i>${product.description}</i> <br>
	<img src="data:image/jpg;base64,${product.base64Image}" />
</body>
</html>