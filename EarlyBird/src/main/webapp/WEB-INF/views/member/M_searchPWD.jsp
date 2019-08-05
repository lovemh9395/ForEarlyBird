<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<<<<<<< HEAD
<!DOCTYPE html>
<html lang="en">
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
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
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="../include/core.jsp"%>
	<!--  end Core -->
</body>
</html>
=======
<script>
	$(document).ready(function() {
		$("#searchPWDdetail").click(function() {
			document.getElementById('searchPWDform').submit();
		});
	});
</script>
<div class="modal fade" id="modal-searchPWD" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div class="modal-dialog modal- modal-dialog-centered modal-sm"
		role="document">
		<div class="modal-content">
			<div class="modal-body p-0">
				<div class="card bg-secondary shadow border-0">
					<div class="card-body px-lg-5 py-lg-5">
						<div class="text-center text-muted mb-4">
							<a class="navbar-brand pt-0" href="${contextPath}/"> <img
								src="/resources/assets/img/brand/logo.png"
								class="navbar-brand-img" alt="..."
								style="width: 200px; height: auto;">
							</a>
						</div>
						<form role="form" id="searchPWDform" action="member/M_searchPWD"
							method="post">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<c:if test="${empty FindId}">
										<input class="form-control" placeholder="이메일 아이디"
											 name="mem_userid" value="">
									</c:if>
									<c:if test="${not empty FindId }">
										<input class="form-control"  name="mem_userid"
											value="${FindId }">
									</c:if>

								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="이름"
										name="mem_username">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="생년월일"
										name="mem_birthday">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="전화번호" type="tel"
										 name="mem_phone">
								</div>
							</div>
							<div class="text-center">
								<button type="button" id="searchPWDdetail"
									class="btn btn-primary my-4">비밀번호찾기</button>
								<button type="button" class="btn btn-primary my-4"
									id="cancelSearchPW" data-dismiss="modal">취소하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
>>>>>>> branch-to-kcy
