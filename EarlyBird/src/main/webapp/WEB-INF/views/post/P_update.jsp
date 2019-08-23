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
		<script>
			$(document).ready(function() {
				$("#addP_update").click(function() {
					var post_id =
		<%=request.getParameter("post_id")%>
			var title = $("#post_title").val();
					var content = $("#post_content").val();
					var brd_id =
		<%=request.getParameter("brd_id")%>
			alert(brd_id);
					$.ajax({
						async : false,
						type : "post",
						url : "${contextPath}/post/P_update",
						data : {
							post_title : title,
							post_content : content,
							post_id : post_id,
						},
						success : function(data) {
							location.href = "P_list?brd_id=" + brd_id;
						}
					})
				})
			})
		</script>
		<!-- body -->
		<div class="row mt-5">
			<div class="col">
				<div class="card bg-default shadow">
					<div class="card-header bg-transparent border-0">
						<div class="row">
							<div class="col-1">
								<h3 class="text-white mb-0">글 작성</h3>
							</div>
							<div class="col-11" align="right">
								<button type="button" id="addP_update" class="btn btn-primary">수정하기</button>
								<a
									href="${contextPath }/post/P_detail?post_id=<%=request.getParameter("post_id")%>"><button
										type="button" class="btn btn-primary">취소하기</button></a>
							</div>
						</div>
					</div>
					<div class="table-responsive">
						<form id="P_make" name="P_make" method="post" action="P_make">
							<table class="table align-items-center table-dark table-flush">
								<colgroup>
									<col style="width: 100%">
								</colgroup>
								<thead class="thead-dark">
									<tr>
										<th scope="col" style="font-size: 15px">글 제목</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row" style="padding-left: 40px">
											<div class="media-body">
												<span class="mb-0 text-lg"><input type="text"
													id="post_title" style="width: 1500px;"
													value="${updatePost.post_title }"></span>
											</div>
										</th>
									</tr>
								</tbody>
							</table>
							<table class="table align-items-center table-dark table-flush">
								<colgroup>
									<col style="width: 100%">
								</colgroup>
								<thead class="thead-dark">
									<tr>
										<th scope="col" style="font-size: 15px">글 내용</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td style="font-size: 20px; height: 556px"><span
											class="badge badge-dot mr-4"> <i class="mb-0 text-lg"></i>
												<input type="text" id="post_content"
												style="width: 1500px; height: 500px; font-size: 25px;"
												value="${updatePost.post_content}"></span></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@ include file="../include/main_footer.jsp"%>
		<!-- end footer -->
		<!-- end body -->
	</div>
</body>
</html>