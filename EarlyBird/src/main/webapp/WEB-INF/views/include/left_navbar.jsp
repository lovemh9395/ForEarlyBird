<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	$(function() {
		$("#MOVE_TOP_BTN").click(function() {
			$('html, body').animate({
				scrollTop : 0
			}, 400);
			return false;
		});
	});
</script>
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
		<a class="navbar-brand pt-0" href="${contextPath}/"> <img
			src="/resources/assets/img/brand/logo.png" class="navbar-brand-img"
			alt="..." style="">
		</a>
		<!-- User -->
		<ul class="nav align-items-center d-md-none">
			<li class="nav-item dropdown"><a class="nav-link nav-link-icon"
				href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="ni ni-bell-55"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right"
					aria-labelledby="navbar-default_dropdown_1">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item dropdown"><a class="nav-link" href="#"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
					<div class="media align-items-center">
						<span class="avatar avatar-sm rounded-circle"> <img
							alt="Image placeholder"
							src="/resources/assets/img/theme/team-1-800x800.jpg">
						</span>
					</div>
			</a>
				<div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
					<div class=" dropdown-header noti-title">
						<h6 class="text-overflow m-0">회원님의 정보</h6>
					</div>
					<a href="./examples/profile.html" class="dropdown-item"> <i
						class="ni ni-single-02"></i> <span>내 정보 보기</span>
					</a> <a href="./examples/profile.html" class="dropdown-item"> <i
						class="ni ni-settings-gear-65"></i> <span>내 글 보기</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#!" class="dropdown-item"> <i class="ni ni-user-run"></i>
						<span>Logout</span>
					</a>
				</div></li>
		</ul>
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
			<!-- Navigation -->
			<ul class="navbar-nav">
				<li class="nav-item" class="active"><a
					class=" nav-link active "
					href="${contextPath }/post/P_list?brd_id=1"> <i
						class="ni ni-tv-2 text-primary"></i> 공지사항
				</a></li>
			</ul>
			<hr class="my-3">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link "
					href="${contextPath }/content/C_list_M?brd_id=1"> <i
						class="ni ni-planet text-blue"></i> 게임
				</a></li>
				<li class="nav-item"><a class="nav-link "
					href="${contextPath }/content/C_list_M?brd_id=2"> <i
						class="ni ni-pin-3 text-orange"></i> 영화
				</a></li>
				<li class="nav-item"><a class="nav-link "
					href="${contextPath }/content/C_list_M?brd_id=3"> <i
						class="ni ni-single-02 text-yellow"></i> 드라마
				</a></li>
				<li class="nav-item"><a class="nav-link "
					href="${contextPath }/content/C_list_M?brd_id=4"> <i
						class="ni ni-bullet-list-67 text-red"></i> 음악/공연
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextPath }/content/C_list_M?brd_id=5"> <i
						class="ni ni-key-25 text-info"></i> IT
				</a></li>
			</ul>
			<hr class="my-3">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${contextPath }/post/P_list?brd_id=2"> <i
						class="ni ni-circle-08 text-pink"></i> 자유게시판
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextPath }/post/P_list?brd_id=3"> <i
						class="ni ni-circle-08 text-pink"></i> 게시판
				</a></li>
			</ul>
			<hr class="my-3">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${contextPath }/content/C_share_M?brd_id=5"> <i
						class="ni ni-circle-08 text-pink"></i> 정보공유
				</a></li>
			</ul>
			<hr class="my-3">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href=""> <i
						class="ni ni-circle-08 text-pink"></i> QnA
				</a></li>
			</ul>
			<hr class="my-3">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" id="MOVE_TOP_BTN"
					href="#"> <i
						class="ni ni-circle-08 text-pink"></i>맨 위로 Button
				</a></li>
			</ul>
			<hr class="my-3">
			<!-- Divider -->
			<!-- Heading -->
		</div>
	</div>
</nav>