<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/18
  Time: 16:40
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

    <script type="text/javascript">
        function sub(sid) {
            window.location.href="/showthisstep?sid="+sid;
        }
    </script>
</head>
<body>

<br><br><br><br>
        <c:forEach items="${makeSteps}" var="ms">
            <button class="btn btn-default"><a href="#" onclick="sub(${ms.sid})">${ms.title}</a></button>
            <br>
        </c:forEach>

</body>
</html>
