<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý</title>
<style>
	header,footer{
		height: 100px;
		background-color: #2563EB;
		line-height: 100px;
		text-align: center;
		font-size: 30px;
		color: white;
	}
	
	nav{
		height: 40px;
		text-align: center;
		line-height: 40px;
		background-color: gold;
		font-size: 20px;
	}
	
	.content{
		min-height: 500px;
	}
	
	nav a {
		text-decoration: none;
		padding: 10px;
		color: #2563EB;
	}
	
	nav a:hover{font-weight: bold}
</style>
</head>
<body>
	<header>CÔNG CỤ QUẢN TRỊ TIN TỨC</header>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<nav>
		<a href="${path}/trangchuAdmin">Trang chủ</a> |
		<a href="${path}/tintuc">Tin tức</a> |
		<a href="${path}/loaitin">Loại tin</a> |
		<a href="${path}/nguoidung">Người dùng</a> |
		<a href="${path}/newsletter">Newsletter</a>
	</nav>
	<div class="content">
		<jsp:include page="/views/admin/${page}"></jsp:include>
	</div>
	<footer>Welcome(Họ và tên)</footer>
</body>
</html>