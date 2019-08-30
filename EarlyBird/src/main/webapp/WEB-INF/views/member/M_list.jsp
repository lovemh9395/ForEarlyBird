<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en" id="M_listreplydelete">
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
<body class="">
	<!-- side bar -->
	<%@ include file="../include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="../include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="../include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<div class="row">
			<div class="col">
				<div class="card bg-default shadow">
					<div class="card-header bg-transparent border-0">
						<div class="row">
							<div class="col-1">
								<h3 class="text-white mb-0">내가 쓴 글</h3>
							</div>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table align-items-center table-dark table-flush">
							<colgroup>
								<col style="width: 10%">
								<col style="width: 50%">
								<col style="width: 20%">
								<col style="width: 10%">
								<col style="width: 10%">
							</colgroup>
							<thead class="thead-dark">
								<tr>
									<th scope="col" style="font-size: 15px">글 번호</th>
									<th scope="col" style="font-size: 15px">글 제목</th>
									<th scope="col" style="font-size: 15px">글 작성자</th>
									<th scope="col" style="font-size: 15px">조회수</th>
									<th scope="col" style="font-size: 15px">추천수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="list">
									<c:if test="${list.post_del eq 0 }">
										<tr>
											<th scope="row">
												<div class="media-body">
													<span class="mb-0 text-lg">${list.post_id}</span>
												</div>
											</th>
											<th scope="row">
												<div class="media-body">
													<c:if test="${index eq null}">
														<a
															href="${contextPath }/post/P_detail?page=1&post_id=${list.post_id}&brd_id=${brd_id}"><span
															class="mb-0 text-lg">${list.post_title}</span></a>
													</c:if>
													<c:if test="${index ne null}">
														<a
															href="${contextPath }/post/P_detail?page=${index}&post_id=${list.post_id}&brd_id=${brd_id}"><span
															class="mb-0 text-lg">${list.post_title}</span></a>
													</c:if>
												</div>
											</th>
											<td>
												<div class="avatar-group">
													<span class="mb-0 text-lg">${list.mem_userid}</span>
												</div>
											</td>
											<td><span class="badge badge-dot mr-4"> <i
													class="bg-warning"></i> ${list.post_hit}
											</span></td>
											<td><span class="badge badge-dot mr-4"> <i
													class="bg-warning"></i> ${list.post_like}
											</span></td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="card bg-default shadow">
						<nav aria-label="...">
							<ul class="pagination justify-content-end mb-0">
								<c:if test="${pageMaker.prev }">
									<li class="page-item"><a class="page-link"
										href="${contextPath }/member/M_list?page=${pageMaker.startPage - 1}&perPageNum=${pageMaker.cri.perPageNum}&replypage=${replypageMaker.replyendPage - 1}&replyperPageNum=${replypageMaker.replycri.replyperPageNum}">
											<i class="fas fa-angle-left"></i> <span class="sr-only">Previous</span>
									</a></li>
								</c:if>
								<c:forEach begin="${pageMaker.startPage }"
									end="${pageMaker.endPage }" var="post">
									<li class="page-item active"><a class="page-link"
										href="${contextPath }/member/M_list?page=${post}&perPageNum=${pageMaker.cri.perPageNum}&replypage=${replypageMaker.replycri.replyPage}&replyperPageNum=${replypageMaker.replycri.replyperPageNum}">${post }</a>
									</li>
								</c:forEach>
								<c:if test="${pageMaker.next }">
									<li class="page-item"><a class="page-link"
										href="${contextPath }/member/M_list?page=${pageMaker.endPage + 1}&perPageNum=${pageMaker.cri.perPageNum}&replypage=${replypageMaker.replyendPage + 1}&replyperPageNum=${replypageMaker.replycri.replyperPageNum}">
											<i class="fas fa-angle-right"></i> <span class="sr-only">Next</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
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
														<span class="mb-0 text-sm">${useridd}</span>
													</div>
												</div>
											</th>
											<th scope="row" style="word-break: break-all;">
												<div class="media align-items-center">
													<div class="media-body">
														<div class="row">
															<span class="col-1 mb-0 text-sm"
																style="word-break: break-all;">${list.rpl_content }</span>
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
								<c:if test="${pageMaker.prev }">
									<li class="page-item"><a class="page-link"
										href="${contextPath }/member/M_list?page=${pageMaker.startPage - 1}&perPageNum=${pageMaker.cri.perPageNum}&replypage=${replypageMaker.replyendPage - 1}&replyperPageNum=${replypageMaker.replycri.replyperPageNum}">
											<i class="fas fa-angle-left"></i> <span class="sr-only">Previous</span>
									</a></li>
								</c:if>
								<c:forEach begin="${replypageMaker.replystartPage }"
									end="${replypageMaker.replyendPage }" var="reply">
									<li class="page-item active"><a class="page-link"
										href="${contextPath }/member/M_list?page=${pageMaker.cri.page}&perPageNum=${pageMaker.cri.perPageNum}&replypage=${reply}&replyperPageNum=${replypageMaker.replycri.replyperPageNum}">${reply }</a>
									</li>
								</c:forEach>
								<c:if test="${pageMaker.next }">
									<li class="page-item"><a class="page-link"
										href="${contextPath }/member/M_list?page=${pageMaker.endPage + 1}&perPageNum=${pageMaker.cri.perPageNum}&replypage=${replypageMaker.replyendPage + 1}&replyperPageNum=${replypageMaker.replycri.replyperPageNum}">
											<i class="fas fa-angle-right"></i> <span class="sr-only">Next</span>
									</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<!-- end body -->
		</div>
		<!-- footer -->
		<%@ include file="../include/main_footer.jsp"%>
		<!-- end footer -->
		<!--   Core   -->
		<%@ include file="../include/core.jsp"%>
		<!--  end Core -->
</body>
</html>