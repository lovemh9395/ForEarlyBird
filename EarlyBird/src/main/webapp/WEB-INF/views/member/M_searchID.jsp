<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<script>
	$(document).ready(function() {
		$("#UsersearchID").click(function() {
			document.getElementById('UsersearchForm').submit();
			$("#searchIDmodal").modal("hide");
			$("body").css({"padding-right": "0px"}).removeClass("modal-open");
			$(".modal-backdrop").remove();
		})
	})
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<base target="_self"/>
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
						<iframe src="#detailSearchID" name="iframe" style="width:1px; height:1px; border:0; visibility:hidden;"></iframe>
						<form role="form" id="UsersearchForm" action = "member/M_searchID" target="iframe"
							method="post">
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
								<a href="#detailSearchID" data-toggle="modal"><button type="button" class="btn btn-primary my-4"
									id="UsersearchID">아이디 찾기</button>
									</a>
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
<%@ include file="M_detailSearchID.jsp"%>