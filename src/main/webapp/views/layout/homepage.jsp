<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Báo Online</title>
<style>
header, footer {
	height: 100px;
	background-color: #2563EB;
	line-height: 100px;
	text-align: center;
	font-size: 30px;
	color: white;
}

nav {
	height: 40px;
	text-align: center;
	line-height: 40px;
	background-color: gold;
	font-size: 20px;
}

.content {
	min-height: 500px;
}

nav a {
	text-decoration: none;
	padding: 10px;
	color: #2563EB;
}

nav a:hover {
	font-weight: bold
}

.container {
	display: flex;
	min-height: 500px;
}

.content {
	flex: 3;
	padding: 20px;
	border: 1px solid #ddd;
}

.sidebar {
	flex: 1;
	display: flex;
	flex-direction: column;
}

.box {
	border: 2px solid gold;
	flex: 1;
	padding: 10px;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 16px;
	font-weight: bold;
	text-align: center;
}

.box a {
	text-decoration: none;
	color: inherit;
	display: block;
	width: 100%;
	text-align: center;
}

.box1 {
	background-color: orange;
}

.box2 {
	background-color: gray;
	color: white;
}

.box3 {
	background-color: green;
	color: white;
}

.box4 {
	display: flex;
	align-items: center;
	justify-content: center;
}

.newsletter-input {
	width: 100%;
	height: 40px;
	padding: 5px 10px;
	font-size: 16px;
	border: none;
	outline: none;
}

.news-list {
	list-style: none;
	margin: 0;
	padding: 10px 15px;
	background-color: #fff;
	border-left: 2px solid gold;
	border-right: 2px solid gold;
	border-bottom: 2px solid gold;
}

.news-list li {
	margin-bottom: 6px;
	border-bottom: 1px dashed #ccc;
	padding-bottom: 4px;
}

.news-list li:last-child {
	border-bottom: none;
}

.news-list li a {
	text-decoration: none;
	color: #2563EB;
	font-size: 15px;
	transition: all 0.2s ease;
}

.news-list li a:hover {
	font-weight: bold;
	color: #1E3A8A;
	text-decoration: underline;
}
</style>
</head>
<body>
	<header>Header</header>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<nav>
		<a href="${path}/trangchu">Trang chủ</a> | 
		<a href="${path}/vanhoa">Văn hoá</a> | 
		<a href="${path}/phapluat">Pháp luật</a> | 
		<a href="${path}/thethao">Thể thao</a> | 
		<a href="${path}/dangnhap">Đăng nhập</a>
	</nav>

	<div class="container">
		<div class="content">
			<jsp:include page="/views/layout/${page}"></jsp:include>
		</div>

		<aside class="sidebar">
			<!-- Box: Xem nhiều -->
			<div class="box box1">5 Bản tin được xem nhiều</div>
			<ul class="news-list">
				<c:forEach var="item" items="${xemNhieuList}">
					<li><a href="${path}/chitiet?id=${item.id}"> ${item.title}
					</a></li>
				</c:forEach>
			</ul>

			<!-- Box: Mới nhất -->
			<div class="box box2">5 Bản tin mới nhất</div>
			<ul class="news-list">
				<c:forEach var="item" items="${moiNhatList}">
					<li><a href="${path}/chitiet?id=${item.id}"> ${item.title}
					</a></li>
				</c:forEach>
			</ul>

			<!-- Box: Đã xem -->
			<div class="box box3">5 Bản tin bạn đã xem</div>
			<ul class="news-list">
				<c:forEach var="item" items="${daXemList}">
					<li><a href="${path}/chitiet?id=${item.id}"> ${item.title}
					</a></li>
				</c:forEach>
			</ul>

			<!-- Ô newsletter -->
			<div class="box box4">
				<input type="text" class="newsletter-input" placeholder="Newsletter">
			</div>
		</aside>
	</div>
	<!-- 	<div class="content"> -->
	<%-- 		<jsp:include page="/views/layout/${page}"></jsp:include> --%>
	<!-- 	</div> -->
	<footer>Footer</footer>
</body>
</html>