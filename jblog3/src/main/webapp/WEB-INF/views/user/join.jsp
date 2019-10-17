<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src = "http://code.jquery.com/jquery-latest.js"></script>
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/event.js" type="text/javascript"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form:form
		 modelAttribute="userVo"
		 class="join-form"
		  method="post"
		  action="${pageContext.servletContext.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<!-- <input id="name" name="name" type="text" value=""> -->
			<form:input path="name" id="name"/>
			<spring:hasBindErrors name="userVo">
				<c:if test='${errors.hasFieldErrors("name") }'>
				<p style="font-weight:bold;color:red; text-align:left; padding-left:0">
				<spring:message code='${errors.getFieldError("name").codes[0] }' text='${errors.getFieldError("name").defaultMessage }'/>
				</p>
				</c:if>
			</spring:hasBindErrors>
			<label class="block-label" for="id">아이디</label>
			<!-- <input id="blog-id" name="id" type="text"> -->
			<form:input path="id" id="id"/>
			<p style="font-weight:bold;color:red; text-align:left; padding:2px 0 0 0 ">
				<form:errors path="id"/>
			</p>
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<!-- <input id="password" name="password" type="password" /> -->
			<form:password path='password' id="password"/>
			<spring:hasBindErrors name="userVo">
				<c:if test='${errors.hasFieldErrors("password") }'>
				<p style="font-weight:bold;color:red; text-align:left; padding-left:0">
				<spring:message code='${errors.getFieldError("password").codes[0] }' text='${errors.getFieldError("password").defaultMessage }'/>
				</p>
				</c:if>
			</spring:hasBindErrors>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
