<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/18
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <%--    <script src="https://cdn.bootcss.com/popper.js/1.14.0/popper.min.js"></script>--%>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<br><br><br><br><br>
<%--<a href="/">首页</a>
<a href="/test2">添加文章</a>
<a href="/toMakeStep">分享美食制作步骤</a>
<a href="/toSeeMakeStep">查看美食制作步骤</a>--%>

    <c:forEach items="${list}" var="list" varStatus="l">
        <c:if test="${l.count % 2 !=0}">
            <p>${list}</p>
        </c:if>
        <c:if test="${l.count % 2 ==0}">
            <img src="${pageContext.request.contextPath}/img/${list}" class="img-responsive" style="width: 100px;height: 100px"/>
        </c:if>
    </c:forEach>

</body>
</html>
