<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="row" id="R_replylist">
	<div class="col">
		<div class="card shadow">
			<div class="card-header border-0">
				<h3 class="mb-0">댓글</h3>
			</div>
			<div class="table-responsive" id="R_list">
				<table class="table align-items-center table-flush"
					style="table-layout: fixed">
					<thead class="thead-light">
					<colgroup>
						<col style="width: 15%">
						<col style="width: 70%">
						<col style="width: 15%">
					</colgroup>
					<tr>
						<th scope="col" style="font-size: 15px">작성자</th>
						<th scope="col" style="font-size: 15px">작성내용</th>
						<th scope="col" style="font-size: 15px">작성시간</th>
					</tr>
					<tbody>
						<c:forEach items="${R_list}" var="list" varStatus="status">
							<c:if test="${list.rpl_del eq 0 }">
								<tr>
									<th scope="row">
										<div class="media align-items-center">
											<div class="media-body">
												<span class="mb-0 text-sm">${list.mem_id }</span>
											</div>
										</div>
									</th>
									<th scope="row" style="word-break: break-all;">
										<div class="media align-items-center">
											<div class="media-body">
												<div class="row">
													<span class="col-1 mb-0 text-sm"
														style="word-break: break-all;">${list.rpl_content }</span>
													<div class="col-11" align="right">
														<c:if test="${ list.mem_id eq useridd}">
															<%@ include file="R_delete.jsp"%>
														</c:if>
													</div>
												</div>
											</div>
										</div>
									</th>
									<th scope="row">
										<div class="media align-items-center">
											<div class="media-body">
												<span class="mb-0 text-sm">${list.rpl_datetime }</span>
											</div>
										</div>
									</th>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card-footer py-4">
				<nav aria-label="...">
					<ul class="pagination justify-content-end mb-0">
						<li class="page-item"><a class="page-link"
							href="${contextPath }/post/P_detail?page=${pageMaker.startPage - 1}&perPageNum=${pageMaker.cri.perPageNum}&post_id=${P_detail.post_id}">
								<i class="fas fa-angle-left"></i> <span class="sr-only">Previous</span>
						</a></li>
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }" var="index">
							<li class="page-item active"><a class="page-link"
								href="${contextPath }/post//P_detail?page=${index}&perPageNum=${pageMaker.cri.perPageNum}&post_id=${P_detail.post_id}">${index }</a>
							</li>
						</c:forEach>

						<li class="page-item"><a class="page-link"
							href="${contextPath }/post//P_detail?page=${pageMaker.endPage + 1}&perPageNum=${pageMaker.cri.perPageNum}&post_id=${P_detail.post_id}">
								<i class="fas fa-angle-right"></i> <span class="sr-only">Next</span>
						</a></li>
					</ul>
				</nav>
			</div>
			<%@ include file="R_make.jsp"%>
		</div>
	</div>
</div>
