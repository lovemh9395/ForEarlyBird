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
			href="${contextPath}/admin/A_mainpage">ForEarlyBird</a>
		<!-- User -->
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
					<a href="${contextPath }/admin/A_mainpage" class="dropdown-item">
						<i class="ni ni-settings-gear-65"></i> <span>관리자 페이지</span>
					</a> <a href="${contextPath}/" class="dropdown-item"> <i
						class="ni ni-settings-gear-65"></i> <span>메인 페이지</span>
					</a> <a href="${contextPath}/member/M_logout" class="dropdown-item">
						<i class="ni ni-user-run"></i> <span>로그아웃</span>
					</a>
				</div></li>
		</ul>
	</div>
</nav>
