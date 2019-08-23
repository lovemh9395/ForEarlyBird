<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Modal -->
<style>
.modal-backdrop {
	z-index: auto;
}
</style>
<script>
	$(document).ready(function() {
		$("#loginbutton").click(function() {
			if (formChecking()) {
				var login_id = $("#login_mem_userid").val();
				var login_pass= $("#login_mem_password").val();
				$.ajax({
					type:"post",
					url:"${contextPath}/member/login_Check",
					data:{"login_id":login_id,
						  "login_pass":login_pass},
						  success:function(data) {
						  		 if (data == 1) {
						  			alert("없는 아이디 입니다.");
						  		} else if (data == 2) {
						  			alert("비밀번호가 맞지 않습니다.");
						  		} else {
						  		$('#loginform').submit();
						  		}
						  }
				})
			}
		});
	});

	function formChecking() {
		var expemail = /\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b/;
		if (!expemail.test($("#login_mem_userid").val())) {
			alert("이메일 형식이 맞지 않습니다.");
			return false;
		}
		var exppass = /^[a-zA-Z0-9]{7,15}$/;
		if (!exppass.test($("#login_mem_password").val())) {
			alert("비밀번호는 8~15자의 영문,숫자만 허용됩니다.");
			return false;
		}
		return true;
	}
</script>
<div class="modal fade" id="modal-login" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div id="modal-login"
		class="modal-dialog modal-dialog-centered modal-sm" role="document">
		<div class="modal-content" id="modal-content-login">
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
						<form role="form" id="loginform"
							action="${contextPath }/member/M_login" method="post">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="Email" type="email"
										id="login_mem_userid" name="login_mem_userid">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password"
										type="password" id="login_mem_password" name="login_mem_password">
								</div>
							</div>
							<div class="text-center">
								<button type="button" id="loginbutton"
									class="btn btn-primary my-4">로그인</button>
								<div class=row>
									<div class="col-md-6">
										<button type="button" class="btn btn-block btn-info"
											data-toggle="modal" id="searchID"
											data-target="#searchIDmodal">ID찾기</button>
									</div>
									<div class="col-md-6">
										<button type="button" class="btn btn-block btn-info"
											data-toggle="modal" id="searchPW"
											data-target="#modal-searchPWD">PW찾기</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="M_searchID.jsp"%>
<%@ include file="M_searchPWD.jsp"%>