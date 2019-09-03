<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Modal -->
<style>
.modal-backdrop {
	z-index: auto;
}

.modal-backdrop {
	z-index: -1;
}

.file_input_textbox {
	float: left
}

.file_input_div {
	position: relative;
	width: 100px;
	height: 30px;
	overflow: hidden;
}

.file_input_button {
	width: 100px;
	position: absolute;
	top: 0px;
	background-color: #aaa;
	color: #fff;
	border-style: solid;
}

.file_input_hidden {
	font-size: 70px;
	position: absolute;
	right: 0px;
	top: 0px;
	opacity: 0;
	filter: alpha(opacity = 0);
	-ms-filter: "alpha(opacity=0)";
	-khtml-opacity: 0;
	-moz-opacity: 0;
}
</style>
<script>
	$(document).ready(function() {
		$("#share_upload_button").click(function() {
			if ($("#share_file").val() != null) {
				console.log($("#share_file").val());
				document.getElementById('share_upload').submit();
			} else {
				alert("사진을 넣어주세요");
				return false;
			}
		});
	});
</script>
<div class="modal fade" id="modal_share" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div id="share_modal"
		class="modal-dialog modal-dialog-centered modal-sm" role="document">
		<div class="modal-content" id="modal-content-login">
			<div class="modal-body p-0">
				<div class="card bg-secondary shadow border-0">
					<div class="card-body px-lg-5 py-lg-5">
						<div class="text-center text-muted mb-4">
							<a class="navbar-brand pt-0"> <img
								src="/resources/assets/img/brand/logo.png"
								class="navbar-brand-img" alt="..."
								style="width: 200px; height: auto;">
							</a>
						</div>
						<form id="share_upload" method="post"
							action="${contextPath }/content/C_share_make"
							enctype="multipart/form-data">
							<input type="hidden" name="brd_id" value="<%=request.getParameter("brd_id") %>">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="타이틀" type="text"
										id="share_title" name="share_title">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="link" type="text"
										id="share_link" name="share_link">
								</div>
							</div>
							<div class="form-group">
								<input type="text" id="share_file" class="file_input_textbox"
									readonly="readonly">
								<div class="file_input_div">
									<input type="button" value="파일선택" class="btn btn-sm btn-info">
									<input type="file" class="file_input_hidden" name="file2"
										onchange="javascript:document.getElementById('share_file').value = this.value.split('\\')[this.value.split('\\').length-1]">
								</div>
							</div>
							<div class="text-center">
								<input type="button" id="share_upload_button"
									class="btn btn-primary my-4" value="글쓰기">
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
