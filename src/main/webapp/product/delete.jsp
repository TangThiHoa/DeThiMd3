<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 6/3/2022
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Product</h1>
<p>
    <a href="/products">Back to student list</a>
</p>
<a href="/products?act=delete&id=${st.id}">xóa</a>
<table>
    <tr>
        <td>Name: </td>
        <td>${st.name}</td>
    </tr>
    <tr>
        <td>Price: </td>
        <td>${st.price}</td>
    </tr>
    <tr>
        <td>Color: </td>
        <td>${st.color}</td>
    </tr>
    <tr>
        <td>Description: </td>
        <td>${st.description}</td>
    </tr>
<%--    <tr>--%>
<%--        <td>Category: </td>--%>
<%--        <td>${st.Category.getid()}</td>--%>
<%--    </tr>--%>






</table>
</body>
</html>
