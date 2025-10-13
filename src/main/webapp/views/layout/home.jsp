<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ</title>
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
	<h2>TRANG CHỦ</h2>
<c:forEach var="n" items="${newsList}">
    <div class="news-item" style="display:flex; margin-bottom:15px; border-bottom:1px solid #ddd; padding-bottom:10px;">
        <div class="news-img" style="width:120px;height:90px;background-color:#2563EB;color:white;display:flex;align-items:center;justify-content:center;font-weight:bold;margin-right:15px;flex-shrink:0;">
            <c:choose>
                <c:when test="${not empty n.image}">
                    <img src="${pageContext.request.contextPath}/image/${n.image}" width="120" height="90" alt="Ảnh"/>
                </c:when>
                <c:otherwise>Ảnh</c:otherwise>
            </c:choose>
        </div>

        <div class="news-content" style="flex:1;">
            <a href="${pageContext.request.contextPath}/chitiet?id=${n.id}" class="news-title" style="color:#2563EB;font-weight:bold;text-decoration:none;">
                ${n.title}
            </a>
            <p class="news-desc" style="font-style:italic;color:#333;margin:4px 0;">
                <c:choose>
                    <c:when test="${fns:length(n.content) > 100}">
                        ${fns:substring(n.content,0,100)}...
                    </c:when>
                    <c:otherwise>${n.content}</c:otherwise>
                </c:choose>
            </p>
            <p class="news-meta" style="font-style:italic;color:gray;font-size:14px;">
                Ngày đăng: <fmt:formatDate value="${n.postedDate}" pattern="dd/MM/yyyy"/> | Tác giả: ${n.author} | Lượt xem: ${n.viewCount}
            </p>
        </div>
    </div>
</c:forEach>

</body>
</html>