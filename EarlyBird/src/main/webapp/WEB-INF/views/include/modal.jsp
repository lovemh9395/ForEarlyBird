<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Modal -->
<style>
.modal-backdrop {
	z-index: -1;
}
</style>
<script>
	$(document).ready(function() {
		$("#M_login").click(function() {
			var id = $("#email").val();
			var pw = $("#password").val();
			var query = {
				"id" : id,
				"pw" : pw
			}
			$.ajax({
				type : "get",
				url : "member/login",
				data : query,
				success : function(data) {
					if (data == -1) {
						alert("아이디가 존재하지 않습니다.")
					} else if (data == 0) {
						alert("비밀번호가 일치하지 않습니다.")
					} else {
						alert("로그인 성공!");
						sessionStorage.setItem('user', id);
						window.location.href = "/";
						// response.sendRedirect("main.jsp");
					}
				}
			});
		});
	});
</script>
<button type="button" class="btn btn-block btn-info" data-toggle="modal"
	data-target="#modal-form">로그인</button>
<div class="modal fade" id="modal-form" tabindex="-1" role="dialog"
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
						<form role="form" id="form" action="http://localhost:8081/">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="Email" type="email"
										id="email">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password"
										type="password" id="password">
								</div>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id=" customCheckLogin"
									type="checkbox"> <label class="custom-control-label"
									for=" customCheckLogin"><span>Remember me</span></label>
							</div>
							<div class="text-center">
								<button type="button" id="M_login" class="btn btn-primary my-4">로그인</button>
								<button type="button" class="btn btn-primary my-4">ID/PW찾기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>