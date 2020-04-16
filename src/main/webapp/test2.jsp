<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2020/4/14
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

<head>
    <title>Upload Multiple Files</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <style type="text/css">
        .progress{
            height:20px;
            width:70%;
            position: relative;
        }
        .progress span{
            display: block;
            height:20px;
            width:0;
            color: #000;
            font-size: 12px;
        }
        .progress button{
            position: absolute;
            top: 0;
            right: -60px;
        }

        .red{
            background-color: red;
        }

        .green{
            background-color: green;
        }
        .img-box{
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
        }
        .img-box .item{
            width: 300px;
            height:200px;
            border:solid 1px #ccc;
            margin-bottom: 20px;
        }
        .img-box .item img{
            width: 100%;
            height:80%;
        }

        .addfile{
            width: 60px;
            height:60px;
            background: #000000;
            color: #fff;
            font-size: 14px;
            text-align: center;
            line-height: 50px;
            cursor: pointer;
            position: relative;
        }
        .addfile input{
            position: absolute;
            left: 0;
            right: 0;
            bottom: 0;
            top: 0;
            width: 100%;
            opacity: 0;

        }

    </style>

    <link rel="stylesheet" href="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.14.0/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        function test() {
            //第二个参数必须要加new Date().getTime()才能实时读写
            var file = new File([files[0]], new Date().getTime() + "_pic.jpg", {
                type: files[0].type
            });
        }
    </script>
</head>
<body>

<br><br><br><br><br><br><br>
<a href="/">首页</a>

<h1>图片上传Demo</h1><!--action="fileUpload"-->
<form action="fileUpload" method="post" enctype="multipart/form-data" id="fileUploadForm">
    <!--    <p>选择文件: <input type="file" name="fileName"/></p>-->

    <input name="description" placeholder="清添加描述" type="text" />

    <input type="file" class="form-control" id="files"
           placeholder="Upload Multiple Files"  name="fileName" multiple></input>


    <!--    <p><input type="submit" value="提交"/></p>-->

    <div class="img-box"></div>


    <button type="submit" class="btn btn-default" id="btnSubmit">上传</button>


</form>





<%--
<img src="../img/1.jpg" style="width: 300px;height: 300px" />--%>

</body>
</html>



<script>
    //更改网络 为慢3g，就可以比较明显的看到进度条了
    var fileMaxCount=9;
    var imgBox =document.getElementsByClassName('img-box')[0];
    var willUploadFile=[];//保存待上传的文件以及相关附属信息
    document.getElementById('files').addEventListener('change',function (e) {


        var fileList = document.getElementById('files').files;

        if (willUploadFile.length > fileMaxCount || fileList.length>fileMaxCount || (willUploadFile.length+ fileList.length>fileMaxCount)) {
            alert('最多只能上传' + fileMaxCount + '张图');
            return;
        }
        for (var i = 0; i < fileList.length; i++) {
            var f = fileList[i];//先预览图片


            var img = document.createElement('img');
            var item = document.createElement('div');
            var progress = document.createElement('div');
            progress.className='progress';
            progress.innerHTML = '<span class="red"></span><button type="button">Abort</button>';
            item.className='item';
            img.src = window.URL.createObjectURL(f);
            img.onload = function () {
                //显示要是否这块儿内存
                window.URL.revokeObjectURL(this.src);
            }

            item.appendChild(img);
            item.appendChild(progress);
            imgBox.appendChild(item);

            willUploadFile.push({
                file:f,
                item,
                progress
            });
        }
    });


    function xhrSend({file, progress}) {

        var progressSpan = progress.firstElementChild;
        var btnCancel = progress.getElementsByTagName('button')[0];

        btnCancel.removeEventListener('click',function(e) {

        });
        btnCancel.addEventListener('click',function(e) {
            if(xhr && xhr.readyState!==4){
                //取消上传
                xhr.abort();
            }
        });

        progressSpan.style.width='0';
        progressSpan.classList.remove('green');

        var fd = new FormData();   //构造FormData对象
        fd.append('files',file);
        // fd.append('files',file,new Data().getTime()+".jpg");

        var xhr = new XMLHttpRequest();   //创建对象
        xhr.open('POST', 'http://localhost:8963', true);

        xhr.onreadystatechange = function () {
            console.log('state change', xhr.readyState);
            //调用 abort 后，state 立即变成了4,并不会变成0
            //增加自定义属性  xhr.uploaded
            if (xhr.readyState == 4 &&  xhr.uploaded) {
                var obj = JSON.parse(xhr.responseText);   //返回值
                console.log(obj);
                if(obj.fileUrl.length){
                    //alert('上传成功');
                }
            }
        }

        xhr.onprogress=updateProgress;
        xhr.upload.onprogress = updateProgress;
        function updateProgress(event) {
            if (event.lengthComputable) {
                var completedPercent = (event.loaded / event.total * 100).toFixed(2);
                progressSpan.style.width= completedPercent+'%';
                progressSpan.innerHTML=completedPercent+'%';
                if(completedPercent>90){//进度条变色
                    progressSpan.classList.add('green');
                }
                if(completedPercent>=100){
                    xhr.uploaded=true;
                }
                console.log('已上传',completedPercent);
            }
        }
        //注意 send 一定要写在最下面，否则 onprogress 只会执行最后一次 也就是100%的时候
        xhr.send(fd);//发送时  Content-Type默认就是: multipart/form-data;
        return xhr;
    }

    //文件上传
    function submitUpload(willFiles) {
        if(!willFiles.length){
            return;
        }
        //遍历文件信息进行上传
        willFiles.forEach(function (item) {
            xhrSend({
                file:item.file,
                progress:item.progress
            });
        });
    }
    //绑定提交事件
    document.getElementById('btnSubmit').addEventListener('click',function () {
        submitUpload(willUploadFile);
    });

</script>
