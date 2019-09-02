<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	$(document).ready(function() {
		$("#searchPWDdetail").click(function() {
			var searchPWD = $("#searchPWDform").serialize();
			if (searchPassformCheck()) {
				$.ajax({
					tyep : "post",
					url : "${contextPath}/member/M_searchPWD",
					data : searchPWD,
					success : function(data) {
						if (data == 0) {
							alert("회원정보가 일치하지 않습니다.");
						} else if (data == 1) {
							alert("이메일로 임시비밀번호가 전송되었습니다.");
							location.href = "${contextPath}/";
						}
					}
				})
			}
		});
	});

	function searchPassformCheck() {
		var expemail = /\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b/;
		if (!expemail.test($("input[name='mem_userid']").val())) {
			alert("이메일 형식이 맞지 않습니다.");
			return false;
		}
		var expname = /^[가-힣]{2,4}$/;
		if (!expname.test($("input[name='mem_username']").val())) {
			alert("이름은 한글만 입력이 가능합니다.");
			return false;
		}
		var exptel1 = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
		if (!exptel1.test($("input[name='mem_birthday']").val())) {
			alert("생년월일을 다시 입력해주세요");
			return false;
		}
		var exptel1 = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;
		if (!exptel1.test($("input[name='mem_phone']").val())) {
			alert("전화번호를 다시 입력해주세요");
			return false;
		}
		return true;
	}
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
						<form role="form" id="searchPWDform" method="post">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="이메일 아이디"
										name="mem_userid" value="${FindId }">
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