<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DEMO</title>
</head>

<body>
<p>您好，${user.username}</p>
<h2>用户列表</h2>　

<c:if  test="${!empty users}">
    <table class="data">
        <tr>
            <th>Id</th>
            <th>username</th>
            <th>telephone</th>
            <th>CreateTime</th>
            <th>LastLoginTime</th>
            <th>UpdateTime</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id} </td>
                <td>${user.username} </td>
                <td>${user.telephone} </td>
                <td>${user.createTime} </td>
                <td>${user.lastLoginTime} </td>
                <td>${user.updateTime} </td>

                <td><a href="${pageContext.request.contextPath}/delete?id=${user.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<a href="${pageContext.request.contextPath}/">返回主页</a>

</body>
</html>
