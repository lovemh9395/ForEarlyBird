<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<script>
	$(document).ready(function() {
		$("#UsersearchID").click(function() {
			var username = $("#IDsearchName").val();
			var userbirth = $("#IDsearchBirth").val();
			var userphone = $("#IDsearchTel").val();
			$.ajax({
				async:false,
				type:"post",
				url:"${contextPath}/member/M_searchID",
				data:{"mem_username":username,
						"mem_birthday":userbirth,
						"mem_phone":userphone},
						success:function(data){
							alert(data);
							$(data).modal();
							<%session.removeAttribute("FindId");%>
						}
			})
		})
	});
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<base target="_self" />
<div class="modal fade" id="searchIDmodal" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-sm"
		role="document">
		<div class="modal-content" id="modal-content-searchID">
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
						<form id="UsersearchForm" method="post">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input class="form-control" placeholder="이름" id="IDsearchName"
										name="IDsearchName">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="생년월일"
										id="IDsearchBirth" name="IDsearchBirth">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-lock-circle-open"></i></span>
									</div>
									<input class="form-control" placeholder="전화번호" type="tel"
										id="IDsearchTel" name="IDsearchTel">
								</div>
							</div>
							<div
								class="custom-control custom-control-alternative custom-checkbox">
								<input class="custom-control-input" id=" customCheckLogin"
									type="checkbox"> <label class="custom-control-label"
									for=" customCheckLogin"><span>아이디를 저장합니다.</span></label>
							</div>
							<div class="text-center">
								<button
										type="button" class="btn btn-primary my-4" id="UsersearchID" data-dismiss="modal">아이디
										찾기</button>
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
