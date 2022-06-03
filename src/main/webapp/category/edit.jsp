<%--
  Created by IntelliJ IDEA.
  User: AE
  Date: 6/3/2022
  Time: 2:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  action="/categores?act=edit&id=${sua.id}" method="post">
    <input type="text" name="name" value="${sua.name}">
    <button>Edit</button>

</form>


</body>
</html>
