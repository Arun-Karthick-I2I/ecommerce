<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="AdminHeader.jsp" %>  
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet"
			href="<c:url value='/resources/css/AdminDisplayCategories.css' />">
    </head>
    <body>
        <div class="title">
        	<h2> Category </h2>
        </div>
        <table class="table table-striped">
  			<thead>
    			<tr>
      				<th class="row-header">ID</th>
      		    	<th class="row-header">Name</th>
      		    	<th colspan=2></th>
    			</tr>
  			</thead>
 			<tbody>
    			<tr>
      				<c:forEach items="${categories}" var="category">
                    <form action="category" method="Post">
                        <input type="hidden" name="id" value="${category.id}"/>
                        <tr>
                            <td>${category.id}</td>
                            <td>${category.name}</td>
                            <td>
                                <button type = "submit" formaction = "/ecommerce/category/edit"
                                	formmethod="post"> edit </button> &nbsp;&nbsp;
                                <button type = "submit" formaction = "/ecommerce/category/delete"
                                	formmethod="post"> Delete </button>
                            </td>
                        </tr>
                    </form>
                	</c:forEach>
  			</tbody>
		</table>
    </body>
    <c:if test = "${null != message}">
        <script type="text/javascript">
            alert("${message}");
        </script>
    </c:if>
</html>