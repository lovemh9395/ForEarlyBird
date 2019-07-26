<%@page import="org.omg.PortableInterceptor.USER_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style>
.modal-backdrop {
	z-index: -1;
}
</style>
<nav class="navbar navbar-top navbar-expand-md navbar-dark"
	id="navbar-main">
	<div class="container-fluid">
		<!-- Brand -->
		<a class="h1 mb-1 text-white text-uppercase d-none d-lg-inline-block"
			href="${contextPath}/">ForEarlyBird</a>
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
			<c:if test="${user==null }">
				<div class="col-md-auto">
					<button type="button" class="btn btn-block btn-info"
						data-toggle="modal" data-target="#modal-login">로그인</button>
				</div>
				<div class="col-md-auto">
					<button type="button" class="btn btn-block btn-info"
						data-toggle="modal" data-target="#modal-register">회원가입</button>
				</div>

			</c:if>
			<c:if test="${user!=null }">
				<ul class="navbar-nav align-items-center d-none d-md-flex">
					<li class="nav-item dropdown"><a class="nav-link pr-0"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
							<div class="media align-items-center">
								<span class="avatar avatar-sm rounded-circle"> <img
									alt="Image placeholder"
									src="/resources/uploadimage/${profilephoto}">
								</span>
								<div class="media-body ml-2 d-none d-lg-block">
									<span class="mb-0 text-sm  font-weight-bold">${user.name}</span>
								</div>
							</div>
					</a>
						<div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
							<div class=" dropdown-header noti-title">
								<h6 class="text-overflow m-0">${user.name}님환영합니다</h6>
							</div>
							<a href="${contextPath}/member/M_info?id=${user.id}"
								class="dropdown-item"> <i class="ni ni-single-02"></i> <span>내
									정보 보기</span>
							</a> <a href="${contextPath}/member/M_list" class="dropdown-item">
								<i class="ni ni-settings-gear-65"></i> <span>내 글 보기</span>
							</a>
							<div class="dropdown-divider"></div>
							<a href="${contextPath}/member/M_logout" class="dropdown-item">
								<i class="ni ni-user-run"></i> <span>로그아웃</span>
							</a>
						</div></li>
				</ul>
			</c:if>
		</div>
	</div>
</nav>
<%@ include file="../member/M_login.jsp"%>
<%@ include file="../member/M_make.jsp"%>