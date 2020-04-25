<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/25
  Time: 17:53
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
<h1>我的收藏界面</h1>

<c:if test="${shoucangs == null}">
    还没有内容呢
</c:if>
${message}
<c:if test="${shoucangs != null}">

    <c:forEach items="${shoucangs}" var="sc">
        <table>
            <tr width="600px">
                <td><div name="${sc.scmess.description}">${sc.scmess.description}</div></td>
            </tr>

            <tr>
                <c:forEach items="${sc.scmess.nameList}" var="name" varStatus="cou">

                    <td>
                        <img src="${pageContext.request.contextPath}/img/${name}" class="img-responsive" style="width: 100px;height: 100px"/>
                    </td>
                </c:forEach>

                <td width="50px"></td>

                <td><button class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false" onclick="values(${sc.scmess.mid},0)">评论</button></td>

            </tr>
            <c:forEach items="${sc.scmess.commentList}" var="c">

                <tr style="height: 55px">
                    <c:if test="${c.fatherPerson.pname != null}">
                        <td>${c.childPerson.pname}回复${c.fatherPerson.pname}</td>
                        <td width="50px"></td>
                        <td width="200px">${c.comment}</td>
                    </c:if>
                    <c:if test="${c.fatherPerson.pname == null}">
                        <td>${c.childPerson.pname}</td>
                        <td width="50px"></td>
                        <td width="200px">${c.comment}</td>
                    </c:if>
                    <td width="50px"></td>
                    <td>
                        <c:if test="${c.childPerson.pname != nowName}">
                            <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false" onclick="values(${newMesses.mid},${c.childPerson.pid})">回复</button>
                        </c:if>
                        <c:if test="${c.childPerson.pname == nowName}">
                            <a href="#" onclick="commit(${c.cid})">删除</a>
                        </c:if>
                    </td>
                </tr>

            </c:forEach>


        </table>
        <br><br><br><br><br><br>
    </c:forEach>

</c:if>


<script>
    $(function() {
        var myModal = $('#myModal');
        myModal.modal({
            show: false,
            backdrop: 'static',
            keyboard: false
        })
    });
    $("#myModal").modal("hide");
    function values(mid,pid) {
        $("#mid").val(mid);
        $("#pid").val(pid);
    }
</script>




</body>
</html>
