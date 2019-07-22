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
		$("#join").click(function() {
			document.getElementById('forming').submit();
		});
	});
</script>
<button type="button" class="btn btn-block btn-info" data-toggle="modal"
	data-target="#modal-register">회원가입</button>
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
										id="email" name="makeemail">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password"
										type="password" id="makepassword" name="makepassword">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password2"
										type="password" id="password2" name="makepassword2">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-ruler-pencil"></i></span>
									</div>
									<input class="form-control" placeholder="nickName" type="text"
										id="name" name="makenickname">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-ruler-pencil"></i></span>
									</div>
									<input class="form-control" placeholder="userName" type="text"
										id="name" name="makename">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-air-baloon"></i></span>
									</div>
									<input class="form-control" placeholder="Tel" type="tel"
										id="tel" name="maketel">
								</div>
							</div>
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i class="ni ni-like-2"></i></span>
									</div>
									<input class="form-control" placeholder="Birth" type="text"
										id="birth" name="makebirth">
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