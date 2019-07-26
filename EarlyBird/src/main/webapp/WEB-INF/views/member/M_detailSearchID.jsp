<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<base target="_self"/>
<div class="modal fade" id="detailSearchID" tabindex="-1" role="dialog"
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
						<form role="form" id="UserdetailSearchForm" action="member/M_SearchID" 
							method="post">
							<div class="form-group mb-3">
								<div class="input-group input-group-alternative">
									<div class="input-group-prepend">
										<span class="input-group-text"><i
											class="ni ni-email-83"></i></span>
									</div>
									<input type="text" class="form-control" placeholder="${FindId }" id="IDsearchName"
										name="IDsearchName" value="${FindId }">
										
								</div>
							</div>
							<div class="text-center">
								<button type="button" class="btn btn-block btn-info"
											data-toggle="modal" id="searchPW" data-dismiss="modal"
											data-target="#modal-searchPWD">PW찾기</button>
								<button type="button" class="btn btn-primary my-4" 
									data-dismiss="modal">로그인하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>