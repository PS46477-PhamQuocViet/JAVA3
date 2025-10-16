<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ĐĂNG NHẬP</title>
<style>
.login-container {
	background-color: white;
	padding: 30px;
	border-radius: 15px;
	box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.2);
	width: 350px;
	margin: 30px auto;
	text-align: center;
}

.login-container h2 {
	margin-bottom: 20px;
	color: #2563EB;
}

.form-group {
	margin-bottom: 15px;
	text-align: left;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
	color: #333;
}

.form-group input {
	width: 100%;
	padding: 12px;
	border-radius: 8px;
	border: 1px solid #ddd;
	outline: none;
	transition: 0.3s;
}

.form-group input:focus {
	border-color: #2563EB;
	box-shadow: 0px 0px 5px rgba(37, 99, 235, 0.5);
}

.checkbox-group {
	margin-bottom: 15px;
	font-size: 14px;
}

.btn-login {
	width: 100%;
	background-color: #2563EB;
	color: white;
	padding: 12px;
	border: none;
	border-radius: 8px;
	font-size: 16px;
	cursor: pointer;
	transition: 0.3s;
}

.btn-login:hover {
	background-color: #1E40AF;
}

.extra-links {
	margin-top: 15px;
	font-size: 14px;
}

.extra-links a {
	color: #2563EB;
	text-decoration: none;
}

.extra-links a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2>Đăng nhập</h2>
		<c:if test="${not empty message}">
			<p style="color: red;">${message}</p>
		</c:if>
		<form action="login" method="post">
			<div class="form-group">
				<label for="username">Tên đăng nhập</label> <input type="text"
					id="username" name="username"
					value="${username != null ? username : ''}"
					placeholder="Nhập tên đăng nhập" required>
			</div>
			<div class="form-group">
				<label for="password">Mật khẩu</label> <input type="password"
					id="password" name="password"
					value="${password != null ? password : ''}"
					placeholder="Nhập mật khẩu" required>
			</div>
			<div class="checkbox-group">
				<label> <input type="checkbox" name="remember-me" /> Ghi
					nhớ đăng nhập
				</label>
			</div>
			<button type="submit" class="btn-login">Đăng nhập</button>
		</form>
	</div>
</body>
</html>
