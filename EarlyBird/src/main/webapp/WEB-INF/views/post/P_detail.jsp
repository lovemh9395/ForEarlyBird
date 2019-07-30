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
<script>
	$(document).ready(function(){
		$("#P_recommand").click(function(){
			var post_id= <%= request.getParameter("post_id") %>
			$.ajax({
				type:"post",
				url:"${contextPath}/post/P_recommand",
				data:{post_id:post_id},
				success:function(data){
					location.reload();
				}
			})
		})
	});
</script>
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
								<a
									href="${contextPath }/post/P_detail?post_id=${P_detail.post_id - 1}"><button
										type="button" class="btn btn-primary">이전글</button></a> <a
									href="${contextPath }/post/P_list"><button type="button"
										class="btn btn-primary">목록보기</button></a> <a
									href="${contextPath }/post/P_detail?post_id=${P_detail.post_id + 1}"><button
										type="button" class="btn btn-primary">다음글</button></a>
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
								<tr>
									<th scope="row">
										<div class="media-body">
											<span class="mb-0 text-lg">${P_detail.post_id }</span>
										</div>
									</th>
									<th scope="row">
										<div class="media-body">
											<span class="mb-0 text-lg">${P_detail.post_title }</span>
										</div>
									</th>
									<td>
										<div class="avatar-group">
											<span class="mb-0 text-lg">${P_detail.mem_userid }</span>
										</div>
									</td>
									<td><span class="badge badge-dot mr-4"> <i
											class="bg-warning"></i> ${P_detail.post_hit }
									</span></td>
									<td><span class="badge badge-dot mr-4"> <i
											class="bg-warning"></i> ${P_detail.post_like }
									</span></td>
								</tr>
							</tbody>
						</table>
						<table class="table align-items-center table-dark table-flush">
							<colgroup>
								<col style="width: 100%">
							</colgroup>
							<thead class="thead-dark" align="center">
								<tr>
									<th scope="col" style="font-size: 15px">글 내용</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="font-size: 20px; height: 556px"><span
										class="badge badge-dot mr-4"> <i class="mb-0 text-lg"></i>
											${P_detail.post_content }
									</span></td>
								</tr>
							</tbody>
						</table>

						<div class="row">
							<div align="right" class="col-6">
								<button type="button" class="btn btn-primary" id="P_recommand">추천하기</button>
							</div>
							<c:if test="${useridd eq P_detail.mem_userid }">
								<div align="right" class="col-6">
									<a
										href="${contextPath }/post/P_update?post_id=${P_detail.post_id}"><button
											type="button" class="btn btn-primary">수정하기</button></a>
									<button type="button" class="btn btn-primary"
										data-toggle="modal" id="deletPost" data-target="#P_delete">삭제하기</button>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end body -->
	</div>
</body>
<%@ include file="P_delete.jsp"%>
</html>