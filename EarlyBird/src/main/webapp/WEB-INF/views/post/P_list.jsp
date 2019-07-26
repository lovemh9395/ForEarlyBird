<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!--   Core   -->
<%@ include file="../include/core.jsp"%>
<!--  end Core -->
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
		<div class="row mt-5">
			<div class="col">
				<div class="card bg-default shadow">
					<div class="card-header bg-transparent border-0">
						<div class="row">
							<div class="col-1">
								<h3 class="text-white mb-0">자유 게시판</h3>
							</div>
							<div class="col-11" align="right">
								<button type="button" class="btn btn-primary">글쓰기</button>
							</div>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table align-items-center table-dark table-flush">
							<colgroup>
								<col style="width: 50px">
								<col style="width: 500px">
								<col style="width: 70px">
								<col style="width: 50px">
								<col style="width: 70px">
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
									<tr>
										<th scope="row">
											<div class="media-body">
												<span class="mb-0 text-lg">${list.post_id}</span>
											</div>
										</th>
										<th scope="row">
											<div class="media-body">
												<a
													href="${contextPath }/post/P_detail?page=${index}&post_id=${list.post_id}"><span
													class="mb-0 text-lg">${list.post_title}</span></a>
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
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="card bg-default shadow">
						<nav aria-label="...">
							<ul class="pagination justify-content-end mb-0">
								<li class="page-item"><a class="page-link"
									href="${contextPath }/post/P_list?page=${pageMaker.startPage - 1}&perPageNum=${pageMaker.cri.perPageNum}">
										<i class="fas fa-angle-left"></i> <span class="sr-only">Previous</span>
								</a></li>
								<c:forEach begin="${pageMaker.startPage }"
									end="${pageMaker.endPage }" var="index">
									<li class="page-item active"><a class="page-link"
										href="${contextPath }/post/P_list?page=${index}&perPageNum=${pageMaker.cri.perPageNum}">${index }</a>
									</li>
								</c:forEach>

								<li class="page-item"><a class="page-link"
									href="${contextPath }/post/P_list?page=${pageMaker.endPage + 1}&perPageNum=${pageMaker.cri.perPageNum}">
										<i class="fas fa-angle-right"></i> <span class="sr-only">Next</span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- end body -->
	</div>
</body>
</html>