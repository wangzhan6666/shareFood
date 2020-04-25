<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/25
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<br><br><br>

<a href="/" target="iframe_a">首页</a>
<a href="/test2" target="iframe_a">添加文章</a>
<a href="/toMakeStep" target="iframe_a">分享美食制作步骤</a>
<a href="/toSeeMakeStep" target="iframe_a">查看美食制作步骤</a>
<a href="/myshoucang" target="iframe_a">我的收藏</a>

<form action="/findmakestep" method="post" target="iframe_a">
    <input type="text" name="name" /><button type="submit">美食步骤搜索</button>
</form>

<br><br>
<iframe src="/" width="1500px" height="800px" name="iframe_a"></iframe>

</body>
</html>
