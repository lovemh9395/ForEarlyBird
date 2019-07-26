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
						<h3 class="text-white mb-0">자유 게시판</h3>
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
								<tr>
									<th scope="row">
										<div class="media-body">
											<span class="mb-0 text-lg">${list.post_id}</span>
										</div>
									</th>
									<th scope="row">
										<div class="media-body">
											<a href="${contextPath }/post/P_detail"><span
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
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- end body -->
	</div>
</body>
</html>