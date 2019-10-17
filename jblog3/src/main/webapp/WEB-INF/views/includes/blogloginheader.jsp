<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src = "http://code.jquery.com/jquery-latest.js"></script>
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/event.js" type="text/javascript"></script>
</head>
<body>
	<input type="hidden" value="${authUser.id }" id="userId"/>
	<ul class="admin-menu">
			<li ><a href="${pageContext.request.contextPath}/${authUser.id}/manage">기본설정</a></li>
			<li ><a href="${pageContext.request.contextPath}/${authUser.id}/category">카테고리</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id}/write">글작성</a></li>
	</ul>
</body>
</html>