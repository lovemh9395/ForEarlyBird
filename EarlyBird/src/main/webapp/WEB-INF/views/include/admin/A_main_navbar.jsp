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
		<!-- Brand -->
		<a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block"
			href="./index.html">ForEarlyBird</a>
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
				<button class="btn" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">${user.id}님환영합니다</button>
				<div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
					<div class=" dropdown-header noti-title">
						<h6 class="text-overflow m-0">회원님의 정보</h6>
					</div>
					<a href="./examples/profile.html" class="dropdown-item"> <i
						class="ni ni-single-02"></i> <span>내 정보 보기</span>
					</a> <a href="./examples/profile.html" class="dropdown-item"> <i
						class="ni ni-settings-gear-65"></i> <span>내 글 보기</span>
					</a>
				</div>
			</div>
			<div class="col-md-auto">
				<a href="${contextPath}/member/M_logout"><button
						class="btn btn-info" type="button">로그아웃</button></a>
			</div>
		</div>
	</div>
</nav>