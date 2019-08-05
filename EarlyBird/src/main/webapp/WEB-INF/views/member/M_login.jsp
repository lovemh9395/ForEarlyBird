<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Modal -->
<style>
.modal-backdrop {
<<<<<<< HEAD
	z-index: -1;
=======
	z-index: auto;
>>>>>>> branch-to-kcy
}
</style>
<script>
	$(document).ready(function() {
		$("#loginbutton").click(function() {
<<<<<<< HEAD
			document.getElementById('form').submit();
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
=======
			$('#loginform').submit();
		});
	});
</script>
<div class="modal fade" id="modal-login" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div id="modal-login"
		class="modal-dialog modal-dialog-centered modal-sm" role="document">
		<div class="modal-content" id="modal-content-login">
>>>>>>> branch-to-kcy
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
<<<<<<< HEAD
						<form role="form" id="form" action="member/M_login" method="post">
=======
						<form role="form" id="loginform" action="member/M_login"
							method="post">
>>>>>>> branch-to-kcy
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="Email" type="email"
<<<<<<< HEAD
										id="mem_id" name="mem_id">
=======
										id="email" name="email">
>>>>>>> branch-to-kcy
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="Password"
<<<<<<< HEAD
										type="password" id="mem_password" name="mem_password">
=======
										type="password" id="password" name="password">
>>>>>>> branch-to-kcy
								</div>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id=" customCheckLogin"
									type="checkbox"> <label class="custom-control-label"
									for=" customCheckLogin"><span>아이디를 저장합니다.</span></label>
							</div>
							<div class="text-center">
								<button type="button" id="loginbutton"
									class="btn btn-primary my-4">로그인</button>
<<<<<<< HEAD
								<button type="button" class="btn btn-primary my-4">ID/PW찾기</button>
=======
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
>>>>>>> branch-to-kcy
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<<<<<<< HEAD
</div>
=======
</div>
<%@ include file="M_searchID.jsp"%>
<%@ include file="M_searchPWD.jsp"%>
>>>>>>> branch-to-kcy
