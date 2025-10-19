<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>

<c:url value="/users" var="path"/>

<form method="post">
	<h3>QUẢN LÝ NGƯỜI DÙNG</h3>

	ID : <input name="id" value="${item.id}"><br>
	Mật khẩu : <input type="password" name="password" value="${item.password}"><br>
	Họ tên : <input name="fullname" value="${item.fullname}"><br>

	<fmt:formatDate var="bd" value="${item.birthday}" pattern="yyyy-MM-dd" />
	Ngày sinh : <input type="date" name="birthday" value="${bd}"><br>

	Giới tính :
	<input type="radio" name="gender" value="true" ${item.gender ? 'checked' : ''}> Nam
	<input type="radio" name="gender" value="false" ${!item.gender ? 'checked' : ''}> Nữ <br>

	Số điện thoại : <input name="mobile" value="${item.mobile}"><br>
	Email : <input name="email" value="${item.email}"><br>

	Vai trò :
	<input type="radio" name="role" value="true" ${item.role ? 'checked' : ''}> Quản trị
	<input type="radio" name="role" value="false" ${!item.role ? 'checked' : ''}> Phóng viên <br>

	<hr>
	<button formaction="${path}/create">Thêm</button>
	<button formaction="${path}/update">Cập nhật</button>
	<button formaction="${path}/delete">Xoá</button>
	<button formaction="${path}/reset">Làm mới</button>
</form>

<br>

<!-- TABLE -->
${message} <br>
<table style="width:100%">
	<thead>
		<tr>
			<th>STT</th>
			<th>ID</th>
			<th>Mật khẩu</th>
			<th>Họ tên</th>
			<th>Ngày sinh</th>
			<th>Giới tính</th>
			<th>Điện thoại</th>
			<th>Email</th>
			<th>Vai trò</th>
			<th>Hành động</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="u" items="${list}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${u.id}</td>
				<td>${u.password}</td>
				<td>${u.fullname}</td>
				<td><fmt:formatDate value="${u.birthday}" pattern="dd/MM/yyyy"/></td>
				<td>${u.gender ? 'Nam' : 'Nữ'}</td>
				<td>${u.mobile}</td>
				<td>${u.email}</td>
				<td>${u.role ? 'Quản trị' : 'Phóng viên'}</td>
				<td><a href="${path}/edit/${u.id}">Sửa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>