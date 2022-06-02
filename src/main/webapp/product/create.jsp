<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 6/3/2022
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/products?act=create" method="post">
    <input type="text" name="id">
    <input type="text" name="name">
    <input type="text" name="price">
    <input type="text" name="color">
    <input type="text" name="description">
    <input type="text" name="category">
    <button>Click</button>

</form>
</body>
</html>
