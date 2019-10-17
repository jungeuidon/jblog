<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"/>
		<div id="wrapper">
			<div id="content">
					<c:if test="${empty allPostList && empty postList}">
						<h2>게시글이 존재하지 않습니다.</h2>
					</c:if>
					<c:choose>
						<c:when test='${not empty allPostList}'> <!-- 기본 메인화면 -->
							<div class="blog-content">
								<h4>${defaultPost.title }</h4>
								<p>
									${defaultPost.contents }	
								<p/>
							</div>
							<div>
							<ul class="blog-list">
								<li><h1>전체 글 목록 categoryNo</h1></li>
							</ul>
							</div>
							<ul class="blog-list">
								<c:forEach items="${allPostList }" var="post"  varStatus="status">
									<li><a href="${pageContext.request.contextPath }/${authUser.id }/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.postdate }</span>	</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:when test='${not empty postList && not empty defaultPost}'><!-- 카테고리번호 클릭 -->
							<div class="blog-content">
								<h4>${defaultPost.title }</h4>
								<p>
									${defaultPost.contents }	
								<p/>
							</div>
							<div>
							<ul class="blog-list">
								<li><h1>전체 글 목록 categorynpostno</h1></li>
							</ul>
							</div>
							<ul class="blog-list">
								<c:forEach items="${postList }" var="post"  varStatus="status">
									<li><a href="${pageContext.request.contextPath }/${authUser.id }/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.postdate }</span>	</li>
								</c:forEach>
							</ul>
						</c:when>
						<c:when test='${not empty postInfo && not empty postList}'>
							<div class="blog-content">
								<h4>${postInfo.title }</h4>
								<p>
									${postInfo.contents }	
								<p/>
							</div>
							<div>
							<ul class="blog-list">
								<li><h1>전체 글 목록</h1></li>
							</ul>
							</div>
							<ul class="blog-list">
								<c:forEach items="${postList }" var="post"  varStatus="status">
									<li><a href="${pageContext.request.contextPath }/${authUser.id }/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.postdate }</span>	</li>
								</c:forEach>
							</ul>
						</c:when>
					</c:choose>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/assets/logoimages/${blogVo.logo}" style="width:180px;height:150px">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="category" varStatus="status">
					<li><a href="${pageContext.request.contextPath }/${category.id}/${category.no}">${category.name }</a>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>