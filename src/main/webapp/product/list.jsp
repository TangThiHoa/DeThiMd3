<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 6/2/2022
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/products">
    <input type="text" name="name">
    <button>Click</button>


    <c:forEach items="${ds}" var="p">
        <h2>${p.id},${p.name},
        </h2>

    </c:forEach>
</form>
</body>
</html>
