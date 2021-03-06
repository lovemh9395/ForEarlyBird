<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="P_delete" tabindex="-1" role="dialog"
	aria-labelledby="P_delete" aria-hidden="true">
	<div id="P_delete" class="modal-dialog modal-dialog-centered modal-sm"
		role="document">
		<div class="modal-content" id="modal-content-delete">
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
						<div class="custom-control custom-control-alternative">
							<span>글을 삭제하시겠습니까?</span>
						</div>
						<br>
						<br>
						<div align="center">
							<a href="${contextPath }/post/P_list?post_id=${P_detail.post_id}&brd_id=<%=request.getParameter("brd_id")%>" ><button type="button" class="btn btn-primary">삭제하기</button></a>
							<button type="button" class="btn btn-primary" data-dismiss="modal">취소하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
