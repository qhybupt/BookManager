<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<!DOCTYPE html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="static/css/style.css" />

</head>
<body>
 
<div class="workingroom">
	<div class="addOrEdit" >
		<form action="register" method="post">
			用户名: <input type="text" name="name"> <br>
			邮箱： <input type="text" name="email"><br>
			密码: <input type="password" name="password"> <br><br>
			<input type="submit" value="注册">
		</form>
		<a href="/index"><button>主页</button></a>
	</div>
</div>
</body>
</html>