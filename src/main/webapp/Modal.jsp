<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/17
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <div class="modal fade" tabindex="-1" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <p><button class="close" data-dismiss="modal">&times;</button></p>
<%--                    <h4 class="modal-title">用户登录</h4>--%>
                </div>
                <div class="modal-body">
                    <form action="comment" method="post">
                        <input type="hidden" name="mid" id="mid" value="" />
                        <input type="hidden" name="pid" id="pid" value="" />
                        <div class="form-group">
                            <label form="" class="control-label">评论内容</label>
                            <textarea rows="10" cols="85" name="comment"  class="form-control"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" data-dismiss="modal">取消</button>
                            <button class="btn btn-primary" type="submit">确认</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>


    <br /><br />

</div>

</body>
</html>
