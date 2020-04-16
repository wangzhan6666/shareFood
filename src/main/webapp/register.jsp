<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/16
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="/register" method="post">
        用户名：<input type="text" name="pname" placeholder="请输入用户名" />
        密码：  <input type="password" name="password" placeholder="请输入密码" />

        <button type="submit">注册</button>
    </form>

    <c:if test="${message != null}">
        <p style="color: red;">${message}</p>
    </c:if>

</body>
</html>
