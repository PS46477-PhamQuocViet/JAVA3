<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý loại tin</title>
<style>
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
		padding: 6px;
	}
	th {
		background-color: #eee;
	}
</style>
</head>
<body>
	<c:url var="path" value="/categories" />
	
	<!-- FORM -->
	<h3>Form Loại Tin</h3>
	<form method="post">
		<label>Mã loại:</label><br>
		<input name="id" value="${item.id}"><br>
		
		<label>Tên loại:</label><br>
		<input name="name" value="${item.name}"><br>
		<hr>
		
		<button formaction="${path}/create">Thêm</button>
		<button formaction="${path}/update">Cập nhật</button>
		<button formaction="${path}/delete">Xóa</button>
		<button formaction="${path}/reset">Làm mới</button>
	</form>
	<hr>
	
	<!-- TABLE -->
	<table style="width:70%">
		<thead>
			<tr>
				<th>STT</th>
				<th>Mã loại</th>
				<th>Tên loại</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${c.id}</td>
					<td>${c.name}</td>
					<td>
						<a href="${path}/edit/${c.id}">Sửa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	${message}
</body>
</html>
