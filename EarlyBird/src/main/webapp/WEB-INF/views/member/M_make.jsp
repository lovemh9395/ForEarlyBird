<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<<<<<< HEAD
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
		<form>
			<div class="col-md-3" align="center">
				<label for="exampleInputPassword1"></label>
				<div class="form-group">
					<input type="email" class="form-control" id="mem_userid"
						placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_password"
						placeholder="암호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_password2"
						placeholder="암호를 재입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_username"
						placeholder="이름을 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_phone"
						placeholder="전화번호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="생일을 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="성별을 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="우편번호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="주소를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="상세주소를 입력하세요">
				</div>
				<div class="form-group">
					<label for="exampleInputFile">파일 업로드</label> <input type="file"
						id="exampleInputFile">
					<p class="help-block">여기에 블록레벨 도움말 예제</p>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> 이메일 수신여부
					</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> 정보 공개 여부
					</label>
					<br>
					<button type="submit" class="btn btn-default">제출</button>
				</div>
			</div>
		</form>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="../include/core.jsp"%>
	<!--  end Core -->
</body>
</html>
=======
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- Modal -->

<script>
	$(document).ready(function() {
		$("#join").click(function() {
			document.getElementById('forming').submit();
		});
	});
</script>
<div class="modal fade" id="modal-register" tabindex="-1" role="dialog"
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
						<form role="forming" id="forming" action="member/M_make"
							method="post">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="Email" type="email"
										id="mem_userid" name="mem_userid">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password"
										type="password" id="mem_password" name="mem_password">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password2"
										type="password" id="mem_password2" name="mem_password2">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-ruler-pencil"></i></span>
									</div>
									<input class="form-control" placeholder="nickName" type="text"
										id="mem_nickname" name="mem_nickname">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-ruler-pencil"></i></span>
									</div>
									<input class="form-control" placeholder="userName" type="text"
										id="mem_username" name="mem_username">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-air-baloon"></i></span>
									</div>
									<input class="form-control" placeholder="Tel" type="tel"
										id="mem_phone" name="mem_phone">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="ni ni-like-2"></i></span>
									</div>
									<input class="form-control" placeholder="Birth" type="text"
										id="mem_birthday" name="mem_birthday">
								</div>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id=" customCheckLogin"
									type="checkbox" checked disabled="disabled"> <label
									class="custom-control-label" for=" customCheckLogin"><span>이메일
										수신 여부 (필수항목)</span></label><br>
								<br>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id=" customCheckLogin1"
									type="checkbox"> <label class="custom-control-label"
									for=" customCheckLogin1"><span>정보 공개 동의</span></label>
							</div>
							<div class="text-center">
								<button type="button" id="join" class="btn btn-primary my-4">가입하기</button>
								<button type="button" class="btn btn-primary my-4">취소하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
>>>>>>> branch-to-kcy
