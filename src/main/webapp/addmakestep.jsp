
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>JS动态添加输入框</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <%--    <script src="https://cdn.bootcss.com/popper.js/1.14.0/popper.min.js"></script>--%>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <SCRIPT LANGUAGE="JavaScript">
        var number=0;
        function test(){

            //if($("#tijiao").val() == undefined){ //只能添加一个输入框
                $("#test").append('<div class="form-group"><label for="" class="control-label col-sm-2">步骤：</label><div class="col-sm-4">' +
                    '<textarea rows="10" cols="85" class="form-control" name="'+number+'" /> </div></div>' +
                    '<div class="form-group"><label for="" class="control-label col-sm-2">图片：</label><div class="col-sm-4"><input id="files" type="file" name="fileName"/></div></div>');
            //}
            //alert("执行了"+number+"次");
            $("#length").val(number);

            number += 1;
        }

    </SCRIPT>  

    <%--<script type="text/javascript">
        function submit() {
            window.location.href="/add?number="+number;
        }
    </script>--%>
</head>
<body>
<br><br><br>
<%--<a href="/">首页</a>
<a href="/test2">添加文章</a>
<a href="/toMakeStep">分享美食制作步骤</a>
<a href="/toSeeMakeStep">查看美食制作步骤</a>--%>


<div class="container">

    <form action="/addMakeStep" method="post" enctype="multipart/form-data" class="form-horizontal">

        <div class="form-group">
            <label form="" class="control-label col-sm-2">标题内容：</label>
            <div class="col-sm-4">
                <input type="text" name="title" class="form-control" />
            </div>
        </div>

        <div id="test">

        </div>

        <input id="length" type="hidden" name="len" value="" />


<%--        <a href="#" onclick="submit()">提交</a>--%>
         <button type="submit" class="btn btn-default">上传</button>

    </form>
    <button class="btn btn-success" onclick="test()">添加步骤</button>
</div>
</body>
</html>



<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>点击按钮动态增加输入框数量</title>
</head>
<body>
<script>var i=1</script>
<input type=button onclick="document.body.insertAdjacentHTML('beforeEnd','<input type=text name='+i+' value='+i+++'> ')" value="添加" />
</body>
</html>--%>
