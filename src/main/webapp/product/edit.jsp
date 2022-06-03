<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 6/3/2022
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  action="/products?act=edit&id=${sua.id}" method="post">

    <input type="text" name="name" value="${sua.name}">
    <input type="text" name="price" value="${sua.price}">
    <input type="text" name="color" value="${sua.color}">
    <input type="text" name="description" value="${sua.description}">
    <input type="text" name="category" value="${sua.category.id}">
    <button>Edit</button>

</form>

</body>
</html>
