<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/15
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <%--<script src="https://cdn.bootcss.com/popper.js/1.14.0/popper.min.js"></script>--%>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>

<br><br><br><br><br><br><br><br><br><br>

<a href="/">首页</a>
<a href="/test2">添加文章</a>
<a href="/toMakeStep">分享美食制作步骤</a>
<a href="/toSeeMakeStep">查看美食制作步骤</a>


    <h1>展示发布的页面</h1>

    <span>${description}</span>

    <c:if test="${filesName == null}">
        还没有内容呢
    </c:if>
    <c:if test="${filesName != null}">
        <table>
            <tr width="100px">

                <c:forEach items="${filesName}" var="fn">
                    <td>
                        <img src="${pageContext.request.contextPath}/img/${fn}" style="width: 300px;height: 300px"/>
                        <div name="${fn}">${fn}</div>
                    </td>
                </c:forEach>
            </tr>

        </table>
    </c:if>


</body>
</html>
