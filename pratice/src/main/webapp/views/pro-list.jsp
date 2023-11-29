<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: trann
  Date: 11/28/2023
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2> sản phẩm</h2>
<p>
    <a href="/ProductController?action=add">Add new Prodcut</a>
</p>
<table border="1" cellspacing="0">
    <tr>
        <td>STT</td>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Cat.Name</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach items="${productList}" var="item" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.category.name}</td>
                            <td><a href="/ProductController?action=edit&id=${item.id}">Edit</a></td>
                            <td><a href="/ProductController?action=delete&id=${item.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

