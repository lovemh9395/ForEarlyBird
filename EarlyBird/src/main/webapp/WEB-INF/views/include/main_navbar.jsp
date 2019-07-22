<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<nav class="navbar navbar-top navbar-expand-md navbar-dark"
	id="navbar-main">
	<div class="container-fluid">
		<!-- Form -->
		<form
			class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
			<div class="form-group mb-0">
				<div class="input-group input-group-alternative">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-search"></i></span>
					</div>
					<input class="form-control" placeholder="Search" type="text">
				</div>
			</div>
		</form>
		<!-- User -->
		<div class="row">

			<div class="col-md-auto">
				<c:if test="${user==null }">
					<%@ include file="../member/M_login.jsp"%>
				</c:if>
				<c:if test="${user!=null }">
					${user.id}님 환영합니다
					<div class="col-md-auto">
						<a href="${contextPath}/member/M_logout"><button
								class="btn btn-info" type="button">로그아웃</button></a>
					</div>
				</c:if>
			</div>

		</div>
	</div>
</nav>