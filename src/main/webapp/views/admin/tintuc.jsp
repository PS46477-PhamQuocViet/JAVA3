<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News Management</title>
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

<c:url value="/news" var="path"/>

<form action="${path}/upload" method="post" enctype="multipart/form-data">
	<h3>ĐĂNG TIN TỨC</h3>

	<img src="${pageContext.request.contextPath}/image/${item.image}" style="width:100px;height:80px;" /><br>

	ID : <input name="id" value="${item.id}"> <br>
	Tiêu đề : <input name="title" value="${item.title}"> <br>

	Nội dung : <br>
	<textarea name="content" rows="4" cols="50">${item.content}</textarea><br>

	<fmt:formatDate var="pd" value="${item.postedDate}" pattern="yyyy-MM-dd" />
	Ngày đăng : <input type="date" name="postedDate" value="${pd}"> <br>

	Tác giả : <input name="author" value="${item.author}"> <br>
	Lượt xem : <input name="viewCount" value="${item.viewCount}"> <br>
	Loại tin : 
<select name="categoryId">
	<c:forEach var="cate" items="${categories}">
		<option value="${cate.id}" ${cate.id == item.categoryId ? 'selected' : ''}>
			${cate.name}
		</option>
	</c:forEach>
</select>
<br>

	Hiển thị trang chủ : 
	<input type="radio" name="home" value="true" ${item.home ? 'checked' : ''}> Có
	<input type="radio" name="home" value="false" ${!item.home ? 'checked' : ''}> Không <br>

	Ảnh : <input type="file" name="image" value="${item.image}" onchange="this.form.submit()"/><br>

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
			<th>Tiêu đề</th>
			<th>Nội dung</th>
			<th>Ngày đăng</th>
			<th>Tác giả</th>
			<th>Lượt xem</th>
			<th>Hiển thị trang chủ</th>
			<th>Ảnh</th>
			<th>Loại tin</th>
			<th>Hành động</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="news" items="${list}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${news.id}</td>
				<td>${news.title}</td>
				<td>${news.content}</td>
				<td><fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy"/></td>
				<td>${news.author}</td>
				<td>${news.viewCount}</td>
				<td>${news.home ? '✔' : '✖'}</td>
				<td><img src="${pageContext.request.contextPath}/image/${news.image}" style="width:80px;height:60px;"></td>
				<td>${news.categoryId}</td>
				<td><a href="${path}/edit/${news.id}">Sửa</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>