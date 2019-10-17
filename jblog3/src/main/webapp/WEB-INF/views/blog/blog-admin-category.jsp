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
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blogloginheader.jsp"/>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
						<c:forEach items='${categoryList}' var='category' varStatus="status">
					<tr>
							<td>${category.no }</td>
							<td>${category.name }</td>
							<td>${category.count} 개</td>
							<td>${category.info }</td>
							<td><a href="#" class="delCategory" id ="${category.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
					</tr>  
						</c:forEach>				  
				</table>
				<div id="cartlist"></div>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form:form
      			modelAttribute="categoryVo" id="categoryForm">
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><label class="block-label" for="name" ></label>
			      		<form:input path="name"/></td>
		      			<!-- <td><input type="text" name="name"></td> -->
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><label class="block-label" for="info" ></label>
			      		<form:input path="info"/></td>
		      			<!-- <td><input type="text" name="info"></td> -->
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="button" id="categorysub" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	</form:form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>