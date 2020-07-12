<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="static/css/style.css" />

<title>编辑书目</title>
</head>
<body>



<div class="workingroom">
	<h3>图书列表</h3>
	<a href="/index">返回</a>
	<div class="addOrEdit" >
		<form action="/books/addBook" method="post">
			书名: <input type="text" name="name">
			作者: <input type="text" name="author">
			价格: <input type="text" name="price">
			<input type="submit" value="增加">
		</form>
	</div>
	
	<table border="1" cellpadding="10">
			<tr>
				<td>ID</td>
				<td>书名</td>
				<td>作者</td>
				<td>价格</td>
				<td colspan="2" align="center">编辑书目</td>
			</tr>
		<c:forEach items="${books}" var="book">
			<tr>
				<td><a>${book.id}</a></td>
				<td>《${book.name}》</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td><a href="/books/${book.id}/updateBook">更新</a></td>
				<td><a href="/books/${book.id}/deleteBook">删除</a></td>
			</tr>
		</c:forEach>
		</table>
</div>



</body>
</html>