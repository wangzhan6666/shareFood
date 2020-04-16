<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/15
  Time: 8:09
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

</head>
<body>

<br><br><br><br><br><br><br><br><br><br>

<a href="/">首页</a>
<a href="/test2">添加文章</a>

<h1>展示页面</h1>

<%--<span>${description}</span>--%>

<c:if test="${newMesses == null}">
    还没有内容呢
</c:if>
<c:if test="${newMesses != null}">

            <c:forEach items="${newMesses}" var="newMesses">
                <table>
                <tr width="600px">
                    <td><div name="${newMesses.description}">${newMesses.description}</div></td>
                </tr>

                <tr>
                    <c:forEach items="${newMesses.nameList}" var="name" varStatus="cou">

                        <td width="100px" height="100px">
                            <img src="${pageContext.request.contextPath}/img/${name}" style="width: 100px;height: 100px"/>
                        </td>
                    </c:forEach>
                </tr>
                </table>
                <br><br><br><br><br><br>
            </c:forEach>

</c:if>



</body>
</html>
