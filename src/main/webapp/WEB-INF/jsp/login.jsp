<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<!DOCTYPE html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="static/css/style.css" />


<div class="workingroom">

<div class="errorInfo">${error}</div>
	<form action="login" method="post">
		账号： <input type="text" name="name"> <br>
		密码： <input type="password" name="password"> <br>
		<br>
		<input type="submit" value="登录">
		
	</form>
	<a href="/index"><button>主页</button></a>
</div>
