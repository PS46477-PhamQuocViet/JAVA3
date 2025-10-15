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
	<h2>PHÁP LUẬT</h2>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<c:forEach var="pl" items="${newsList}">
		<div class="news-item"
			style="display: flex; margin-bottom: 15px; border-bottom: 1px solid #ddd; padding-bottom: 10px;">
			<div class="news-img"
				style="width: 120px; height: 90px; background-color: #2563EB; color: white; display: flex; align-items: center; justify-content: center; font-weight: bold; margin-right: 15px; flex-shrink: 0;">
				<c:choose>
					<c:when test="${not empty pl.image}">
						<a href="${path}/chitiet?id=${pl.id}"> <img
							src="${path}/image/${pl.image}" width="120" height="90" alt="Ảnh" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="${path}/chitiet?id=${pl.id}"
							style="color: white; text-decoration: none;">Ảnh</a>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="news-content" style="flex: 1;">
				<a href="${path}/chitiet?id=${pl.id}"
					class="news-title"
					style="color: #2563EB; font-weight: bold; text-decoration: none;">
					${pl.title} </a>
				<p class="news-desc"
					style="font-style: italic; color: #333; margin: 4px 0;">
					<c:choose>
						<c:when test="${fns:length(pl.content) > 100}">
                        ${fns:substring(pl.content,0,100)}...
                    </c:when>
						<c:otherwise>${pl.content}</c:otherwise>
					</c:choose>
				</p>
				<p class="news-meta"
					style="font-style: italic; color: gray; font-size: 14px;">
					Ngày đăng:
					<fmt:formatDate value="${pl.postedDate}" pattern="dd/MM/yyyy" />
					| Tác giả: ${pl.author} | Lượt xem: ${pl.viewCount}
				</p>
			</div>
		</div>
	</c:forEach>
</body>
</html>