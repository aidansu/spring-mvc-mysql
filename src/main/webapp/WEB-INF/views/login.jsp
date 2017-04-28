<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录</title>
</head>

<body>

<div class="container">

    <form class="form-signin" id="userForm" action="" onsubmit="javascript:return false;">
        <h2 class="form-signin-heading">登录</h2>
        <label for="inputUsername" class="sr-only">用户名</label>
        <input name="username" type="text" id="inputUsername" class="form-control" placeholder="用户名">
        <label for="inputPassword" class="sr-only">密码</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="密码" >

        <button id="loginBtn" class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>
    </form>

</div> <!-- /container -->

<div id="alert" style="color: red"></div>

<br>
<a href="${pageContext.request.contextPath}/">返回主页</a>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript" ></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#loginBtn").click(function(){
        var formValue = $("#userForm").serialize();
        $.ajax({
            type: "POST",
            cache: false,
            url:"${pageContext.request.contextPath}/logining",
            data:formValue,
            success: function(msg){
                if( "200" == msg.errcode )  {
                    window.location.href = "${pageContext.request.contextPath}/user-list";
                }else{
                    $("#alert").html("errcode = "+msg.errcode+", errmsg = "+msg.errmsg);
                }
            }
        });
    });
});
</script>

</body>
</html>
