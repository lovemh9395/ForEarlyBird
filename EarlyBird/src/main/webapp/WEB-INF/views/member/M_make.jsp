<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!-- Modal -->

<script>
	$(document).ready(function() {
		$("#join").click(function() {
			if ($("input[id='mem_open_profile']").is(":checked") == true) {
				$("input[name='mem_open_profile']").val("1");
			}
			if (formCheck()) {
				var formId = $("#mem_userid").val();
				$.ajax({
					async : false,
					type : "post",
					url : "${contextPath}/member/M_CheckId",
					data : {
						"formId" : formId
					},
					success : function(data) {
						if (data == 1) {
							var form = $("form[name=forming]").serialize();
							$.ajax({
								async : false,
								type : "post",
								url : "${contextPath }/member/M_make",
								data : form,
								success : function(result) {
									alert("회원가입이 완료되었습니다.");
									location.href="/";
								}
							})
						} else {
							alert("중복된 아이디 입니다.");
						}
					},error:function(data){
						alert(data);
					}
				})
			}
		});
		//$("input[name='dsa']:checked") == true
	});

	//회원가입 폼 제한
	function formCheck() {
		var expemail = /\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b/;
		if (!expemail.test($("#mem_userid").val())) {
			alert("이메일 형식이 맞지 않습니다.");
			return false;
		}
		var exppass = /^[a-zA-Z0-9]{8,15}$/;
		if (!exppass.test($("#mem_password").val())) {
			alert("비밀번호는 8~15자의 영문,숫자만 허용됩니다.");
			return false;
		}
		var exppass2 = /^[a-zA-Z0-9]{8,15}$/;
		if (!exppass2.test($("#mem_password2").val())) {
			alert("비밀번호는 8~15자의 영문,숫자만 허용됩니다.");
			return false;
		}
		var expname = /^[가-힣]{2,6}$/;
		if (!expname.test($("#mem_nickname").val())) {
			alert("닉네임은 한글만 입력이 가능합니다.");
			return false;
		}
		var expname = /^[가-힣]{2,4}$/;
		if (!expname.test($("#mem_username").val())) {
			alert("이름은 한글만 입력이 가능합니다.");
			return false;
		}
		var exptel1 = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;
		if (!exptel1.test($("#mem_phone").val())) {
			alert("전화번호를 다시 입력해주세요");
			return false;
		}
		var exptel1 = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
		if (!exptel1.test($("#mem_birthday").val())) {
			alert("생년월일을 다시 입력해주세요");
			return false;
		}
		if ($("#mem_password").val() != $("#mem_password2").val()) {
			alert("비밀번호가 같지 않습니다.");
			$("#mem_password").val("");
			$("#mem_password2").val("");
			$("#mem_password").focus();
			return false;
		}
		return true;
	}
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
						<form role="forming" id="forming" name="forming">
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
									<input class="form-control" placeholder="ex)19950911"
										type="text" id="mem_birthday" name="mem_birthday">
								</div>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id=" customCheckLogin"
									type="checkbox" checked disabled="disabled"> <label
									class="custom-control-label" for=" customCheckLogin"><span>이메일
										수신 여부 (필수항목)</span></label><br> <br>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id="mem_open_profile"
									type="checkbox"> <label class="custom-control-label"
									for="mem_open_profile"><span>정보 공개 동의</span></label> <input
									type="hidden" name="mem_open_profile" value="0">
							</div>
							<div class="text-center">
								<button type="button" id="join" class="btn btn-primary my-4">가입하기</button>
								<button type="button" class="btn btn-primary my-4"
									data-dismiss="modal">취소하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>