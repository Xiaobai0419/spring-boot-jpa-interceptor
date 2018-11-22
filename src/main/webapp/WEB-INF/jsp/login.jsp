<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/22
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <form method="get" action="/user/login" >
        用户名：<input type="text" name="name"/><br/>
        密码：<input type="password" name="pwd"/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
