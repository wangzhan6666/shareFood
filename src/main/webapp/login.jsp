<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/16
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <form action="/login" method="post">
            用户名：<input type="text" name="pname" placeholder="请输入用户名" />
            密码：  <input type="password" name="password" placeholder="请输入密码" />

            <a href="/toregister">注册</a>
            <button type="submit">登录</button>
        </form>

        <c:if test="${message != null}">
            <p style="color: red;">${message}</p>
        </c:if>


</body>
</html>
