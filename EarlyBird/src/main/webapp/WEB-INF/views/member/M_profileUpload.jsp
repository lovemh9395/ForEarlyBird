<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- Modal -->
<style>
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
		$("#profileupload").click(function() {
			if($("#fileName") == null) {
				alert("넣으셈");
			} else{
			document.getElementById('fileuploadform').submit();
			}
		});
	});
</script>
<button type="button" class="btn btn-sm btn-info mr-4"
	data-toggle="modal" data-target="#modal-image">사진변경</button>
<div class="modal fade" id="modal-image" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div class="modal-dialog modal- modal-dialog-centered modal-sm"
		role="document">
		<div class="modal-content">
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
						<form id="fileuploadform"method="post" action="upload" enctype="multipart/form-data">
							<div class="form-group">
								<input type="text" id="fileName" class="file_input_textbox"
									readonly="readonly">
								<div class="file_input_div">
									<input type="button" value="파일선택" class="btn btn-sm btn-info">
									<input type="file" class="file_input_hidden" name="file1" 
										onchange="javascript:document.getElementById('fileName').value = this.value.split('\\')[this.value.split('\\').length-1]">
								</div>
							</div>
							<div class="text-center">
								<input type="button" id="profileupload" class="btn btn-primary my-4"
									value="사진변경">
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