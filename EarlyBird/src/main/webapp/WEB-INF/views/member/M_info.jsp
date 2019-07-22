<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
<!--   Core   -->
<%@ include file="../include/core.jsp"%>
<!--  end Core -->
<script>
	$(document).ready(function() {
		$("#update").click(function() {
			$("#infoupdate").submit();
			alert("보낸당");
		});
	});
</script>
<body class="">
	<!-- side bar -->
	<%@ include file="../include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="../include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="../include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<div class="container-fluid mt--7">
			<div class="row">
				<div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
					<div class="card card-profile shadow">
						<div class="row justify-content-center">
							<div class="col-lg-3 order-lg-2">
								<div class="card-profile-image">
									<a href="#"> <img
										src="/resources/assets/img/theme/jjangmh.jpg"
										class="rounded-circle">
									</a>
								</div>
							</div>
						</div>
						<div
							class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
							<div class="d-flex justify-content-between">
								<a href="#" class="btn btn-sm btn-info mr-4">사진변경</a> <a
									href="#" class="btn btn-sm btn-default float-right">추천하기</a>
							</div>
						</div>
						<div class="card-body pt-0 pt-md-4">
							<div class="row">
								<div class="col">
									<div
										class="card-profile-stats d-flex justify-content-center mt-md-5">
										<div>
											<span class="heading">22</span> <span class="description">뭐넣을래</span>
										</div>
										<div>
											<span class="heading">10</span> <span class="description">쓴글</span>
										</div>
										<div>
											<span class="heading">89</span> <span class="description">쓴댓글</span>
										</div>
									</div>
								</div>
							</div>
							<div class="text-center">
								<h3>
									${info.mem_username }<span class="font-weight-light">,
										${info.mem_birthday }</span>
								</h3>
								<div class="h5 font-weight-300">
									<i class="ni location_pin mr-2"></i> 여기 집주소
								</div>
								<div class="h5 mt-4">
									<i class="ni business_briefcase-24 mr-2"></i> 여기는 뭐 넣을까
								</div>
								<div>
									<i class="ni education_hat mr-2"></i> 여기도 뭐 넣을까
								</div>
								<hr class="my-4" />
								<p>여기는 오늘의 한마디</p>
							</div>
						</div>
					</div>
					<div class="text-right" align="right">
						<a href="${contextPath }/member/M_delete?id='${info.mem_userid }'"><button
								type="button" class="btn btn-sm btn-primary" id="M_delete">회원탈퇴</button></a>
					</div>
				</div>
				<div class="col-xl-8 order-xl-1">
					<div class="card bg-secondary shadow">
						<div class="card-header bg-white border-0">
							<div class="row align-items-center">
								<div class="col-8">
									<h3 class="mb-0">내 정보</h3>
								</div>
								<div class="col-4 text-right">
									<button type="button" class="btn btn-sm btn-primary"
										id="update">변경하기</button>
								</div>
							</div>
						</div>
						<div class="card-body">
							<form id="infoupdate" method="post"
								action="${contextPath }/member/M_update">
								<h6 class="heading-small text-muted mb-4">${user.name }님의
									상세정보</h6>
								<div class="pl-lg-4">
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="infouserid">아이디</label>
												<input type="text" id="mem_userid" name="mem_userid"
													class="form-control form-control-alternative"
													placeholder="${info.mem_userid }"
													value="${info.mem_userid }">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="input-first-name">이름
												</label> <input type="text" id="infousername" name="infousername"
													class="form-control form-control-alternative"
													placeholder="${info.mem_username }"
													value="${info.mem_username }">
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="input-last-name">닉네임
												</label> <input type="text" name="mem_nickname"
													class="form-control form-control-alternative"
													value="${info.mem_nickname }">
											</div>
										</div>
									</div>
								</div>
								<hr class="my-4" />
								<!-- Address -->
								<h6 class="heading-small text-muted mb-4">연락처 정보</h6>
								<div class="pl-lg-4">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label class="form-control-label" for="input-address">전화번호</label>
												<input name="mem_phone"
													class="form-control form-control-alternative"
													value="${info.mem_phone }" type="text">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-4">
											<div class="form-group">
												<label class="form-control-label" for="input-city">생일</label>
												<input type="text" id="infobirth" name="infobirth"
													class="form-control form-control-alternative"
													placeholder="${info.mem_birthday }"
													value="${info.mem_birthday }">
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<label class="form-control-label" for="input-country">도시</label>
												<input type="text" id="input-country"
													class="form-control form-control-alternative"
													placeholder="Country" value="United States">
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<label class="form-control-label" for="input-country">우편번호
												</label> <input type="number" id="input-postal-code"
													class="form-control form-control-alternative"
													placeholder="Postal code">
											</div>
										</div>
									</div>
								</div>
								<hr class="my-4" />
								<!-- Description -->
								<h6 class="heading-small text-muted mb-4">자기소개</h6>
								<div class="pl-lg-4">
									<div class="form-group">
										<label>자기소개</label>
										<textarea rows="4"
											class="form-control form-control-alternative"
											placeholder="A few words about you ...">여기는 자기소개</textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- Footer -->
			<footer class="footer">
				<div class="row align-items-center justify-content-xl-between">
					<div class="col-xl-6">
						<div class="copyright text-center text-xl-left text-muted">
							&copy; 2019 <a href="https://www.creative-tim.com"
								class="font-weight-bold ml-1" target="_blank">Project 팀이름</a>
						</div>
					</div>
					<div class="col-xl-6">
						<ul
							class="nav nav-footer justify-content-center justify-content-xl-end">
							<li class="nav-item"><a href="https://www.creative-tim.com"
								class="nav-link" target="_blank">필</a></li>
							<li class="nav-item"><a
								href="https://www.creative-tim.com/presentation"
								class="nav-link" target="_blank">요</a></li>
							<li class="nav-item"><a href="http://blog.creative-tim.com"
								class="nav-link" target="_blank">한</a></li>
							<li class="nav-item"><a
								href="https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md"
								class="nav-link" target="_blank">거</a></li>
						</ul>
					</div>
				</div>
			</footer>
		</div>
		<!-- end body -->
	</div>
</body>
</html>