<%--
  Created by IntelliJ IDEA.
  User: jzw
  Date: 2019-05-23
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>问卷系统 | 登录页面</title>

  <!-- Bootstrap core CSS -->
  <link href="static/css/lib/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="static/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-signin">
  <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
  <h1 class="h3 mb-3 font-weight-normal">请登录</h1>
  <label for="inputEmail" class="sr-only">用户名</label>
  <input type="email" id="inputEmail" class="form-control" placeholder="用户名" required autofocus>
  <label for="inputPassword" class="sr-only">密码</label>
  <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> 记住我
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
</form>
</body>
</html>

