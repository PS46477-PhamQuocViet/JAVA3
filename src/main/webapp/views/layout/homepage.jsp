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

    .box1 { background-color: orange; }
    .box2 { background-color: gray; color: white; }
    .box3 { background-color: green; color: white; }
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
            <div class="box box1">
                <a href="${pageContext.request.contextPath}/xemnhieu">5 bản tin được xem nhiều</a>
            </div>
            
            <div class="box box2">
                <a href="${pageContext.request.contextPath}/moinhat">5 bản tin mới nhất</a>
            </div>
            
            <div class="box box3">
                <a href="${pageContext.request.contextPath}/daxem">5 bản tin bạn đã xem</a>
            </div>
            
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