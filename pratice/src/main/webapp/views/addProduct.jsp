<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/24/2023
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body>
<p>
    <a href="/ProductController">Back to Product list</a>
</p>
<h1 class="text-center text-danger">Add new product</h1>
<form action="<%=request.getContextPath()%>/ProductController" method="post">
    <input type="hidden" name="action" value="add">

    <div class="form-group">
        <label for="name">Pro.name: </label>
        <input type="text" class="form-control" id="name" name="pro-name">
    </div>
    <div class="form-group">
        <label for="price">price: </label>
        <input type="text" class="form-control" id="price" name="pro-price">
    </div>
    <div class="form-group">
        <label for="categoryId">Category: </label>
        <select class="form-control" id="categoryId" name="pro-catId">
            <c:forEach var="item" items="${categoryList}">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
</body>
</html>