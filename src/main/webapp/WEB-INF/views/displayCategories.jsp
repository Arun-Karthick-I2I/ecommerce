<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <center>
            <h2> CATEGORY </h2>
        </center>
        <div align="center">
            <table cellpadding="10">
                <tr>
                    <th> ID </th>
                    <th> Name </th>
                    <th></th>
                </tr>
                <c:forEach items="${categories}" var="category">
                    <form action="category" method="Post">
                        <input type="hidden" name="id" value="${category.id}"/>
                        <tr>
                            <td>${category.id}</td>
                            <td>${category.name}</td>
                            <td>
                                <button type = "submit" formaction = "edit"> 
                                    Update </button> &nbsp;&nbsp;
                                <button type = "submit" formaction = "delete"> 
                                    Delete </button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </div> 
    </body>
    <c:if test = "${null != message}">
        <script type="text/javascript">
            alert("${message}");
        </script>
    </c:if>
</html>