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
.news-detail {
	display: flex;
	align-items: flex-start;
	margin: 20px auto;
	width: 90%;
	border-bottom: 2px solid #ddd;
	padding-bottom: 20px;
}

.news-detail img {
	width: 250px;
	height: 180px;
	object-fit: cover;
	border-radius: 8px;
	margin-right: 20px;
	flex-shrink: 0;
}

.news-content {
	flex: 1;
}

.news-content h2 {
	color: #2563EB;
	margin-bottom: 10px;
}

.news-meta {
	font-style: italic;
	color: gray;
	font-size: 14px;
	margin-bottom: 10px;
}

.news-full {
	text-align: justify;
	line-height: 1.6;
	color: #333;
}

/* Tin liên quan */
.related {
	margin: 40px auto;
	width: 90%;
}

.related h3 {
	color: #2563EB;
	margin-bottom: 10px;
}

.related ul {
	list-style-type: square;
	padding-left: 20px;
}

.related a {
	text-decoration: none;
	color: #2563EB;
}

.related a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<div class="news-detail">
		<img src="${path}/image/${news.image}"
			alt="${news.title}">
		<div class="news-content">
			<h2>${news.title}</h2>
			<p class="news-meta">
				Ngày đăng:
				<fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy" />
				| Tác giả: ${news.author} | Lượt xem: ${news.viewCount}
			</p>
			<p class="news-full">${news.content}</p>
		</div>
	</div>

	<!-- Tin cùng loại -->
	<div class="related">
		<h3>Tin cùng loại</h3>
		<ul>
			<c:forEach var="n" items="${relatedNews}">
				<li><a
					href="${path}/chitiet?id=${n.id}">${n.title}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>