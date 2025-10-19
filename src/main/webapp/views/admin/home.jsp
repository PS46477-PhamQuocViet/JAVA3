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
.news-item {
	display: flex;
	align-items: flex-start;
	margin-bottom: 15px;
	border-bottom: 1px solid #ddd;
	padding-bottom: 10px;
}

.news-img {
	width: 120px;
	height: 90px;
	background-color: #2563EB;
	color: white;
	display: flex;
	align-items: center;
	justify-content: center;
	font-weight: bold;
	margin-right: 15px;
	flex-shrink: 0;
}

.news-content {
	flex: 1;
}

.news-title {
	color: #2563EB;
	font-weight: bold;
	text-decoration: none;
}

.news-title:hover {
	text-decoration: underline;
}

.news-desc {
	font-style: italic;
	color: #333;
	margin: 4px 0;
}

.news-meta {
	font-style: italic;
	color: gray;
	font-size: 14px;
}
</style>
</head>
<body>
	<h2>Trang Chủ Tất Cả Tin</h2>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<c:forEach var="n" items="${newsList}">
		<div class="news-item">
			<div class="news-img">
				<c:choose>
					<c:when test="${not empty n.image}">
						<a href="${path}/chitiet?id=${n.id}"> <img
							src="${path}/image/${n.image}" width="120" height="90" alt="Ảnh" />
						</a>
					</c:when>
					<c:otherwise>Ảnh</c:otherwise>
				</c:choose>
			</div>
			<div class="news-content">
				<a href="${path}/chitiet?id=${n.id}" class="news-title">${n.title}</a>
				<p class="news-desc">
					<c:choose>
						<c:when test="${fns:length(n.content) > 100}">
							${fns:substring(n.content,0,100)}...
						</c:when>
						<c:otherwise>${n.content}</c:otherwise>
					</c:choose>
				</p>
				<p class="news-meta">
					Ngày đăng:
					<fmt:formatDate value="${n.postedDate}" pattern="dd/MM/yyyy" />
					| Tác giả: ${n.author} | Lượt xem: ${n.viewCount}
				</p>
			</div>
		</div>
	</c:forEach>
</body>
</html>