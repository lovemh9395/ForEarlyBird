<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav
	class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white"
	id="sidenav-main">
	<div class="container-fluid">
		<!-- Toggler -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#sidenav-collapse-main" aria-controls="sidenav-main"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- Brand -->
		<a class="navbar-brand pt-0" href="#"> <img
			src="/resources/assets/img/brand/logo.png" class="navbar-brand-img"
			alt="..." style="">
		</a>
		<!-- Collapse -->
		<div class="collapse navbar-collapse" id="sidenav-collapse-main">
			<!-- Collapse header -->
			<div class="navbar-collapse-header d-md-none">
				<div class="row">
					<div class="col-6 collapse-brand">
						<a href="./index.html"> <img
							src="/resources/assets/img/brand/blue.png">
						</a>
					</div>
					<div class="col-6 collapse-close">
						<button type="button" class="navbar-toggler"
							data-toggle="collapse" data-target="#sidenav-collapse-main"
							aria-controls="sidenav-main" aria-expanded="false"
							aria-label="Toggle sidenav">
							<span></span> <span></span>
						</button>
					</div>
				</div>
			</div>
			<!-- Form -->
			<form class="mt-4 mb-3 d-md-none">
				<div class="input-group input-group-rounded input-group-merge">
					<input type="search"
						class="form-control form-control-rounded form-control-prepended"
						placeholder="Search" aria-label="Search">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<span class="fa fa-search"></span>
						</div>
					</div>
				</div>
			</form>
			<!-- Navigation --> <!-- nav-link active처리, 페이징 처리시 아이콘변경 -->
			<h6 class="navbar-heading text-muted">site Management</h6>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_noticeList"> 
					<i class="ni ni-air-baloon text-yellow"></i>
						공지사항 관리
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_categoryList"> 
					<i class="ni ni-archive-2 text-indigo"></i>
						카테고리 관리
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_boardList"> 
					<i class="ni ni-bullet-list-67 text-rainbow"></i>
						게시판 관리
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_postList?brd_id=1"> 
					<i class="ni ni-single-copy-04 text-rainbow"></i>
						게시물 관리
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../content/C_list"> 
					<i class="ni ni-controller text-teal"></i>
						컨텐츠 관리
				</a></li>
			</ul>
			<hr class="my-3">
			<h6 class="navbar-heading text-muted">member Management</h6>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_adminList"> <i
						class="ni ni-single-02 text-blue"></i> 관리자 설정
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_memberList"> <i
						class="ni ni-single-02 text-pink"></i> 회원 관리
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="../admin/A_memberList?mem_level=1"> <i
						class="ni ni-circle-08 text-black"></i> 블랙리스트 관리
				</a></li>
			</ul>
			<hr class="my-3">
			<h6 class="navbar-heading text-muted">Summary</h6>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="./examples/register.html"> <i
						class="ni ni-calendar-grid-58 text-gray"></i> 일간 현황 분석
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextPath }/admin/dashboard/"> <i
						class="ni ni-chart-pie-35 text-green"></i> 사이트 요약 분석
				</a></li>
			</ul>
			<!-- Divider -->
			<!-- Heading -->
		</div>
	</div>
</nav>