<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="static/css/style.css" />

<title>图书管理系统</title>
</head>
<body>



<div class="workingroom">
	
	<h3>图书列表</h3>
	<h4 id="status"></h4>

	<c:if test="${empty subject.principal}">
		<a href="login">登录/</a><a href="register">注册</a><br>
	</c:if>
	<c:if test="${!empty subject.principal}">
		<span class="desc">你好，${subject.principal}，</span>
		<a href="doLogout">退出</a><br>	
	</c:if>
		
	<table border="1" cellpadding="10">
			<tr>
				<td>ID</td>
				<td>书名</td>
				<td>作者</td>
				<td>价格</td>
				<td>状态</td>
				<td colspan="2" align="center"><a href="/books/editBook">编辑书目</a></td>
			</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td><a>${book.id}</a></td>
				<td>《${book.name}》</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td><c:if test="${book.status == 0}"> 在库 </c:if>
					<c:if test="${book.status == 1}"> 已借出 </c:if>
				</td>
				<td><a href="/books/${book.id}/borrowBook">借书</a></td>
				<td><a href="/books/${book.id}/returnBook">还书</a></td>
			</tr>
		</c:forEach>
		</table>
</div>



</body>
</html>