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
	$(document).ready(function() {
		$("#P_recommand").click(function() {
			var post_id = <%=request.getParameter("post_id")%>;
			$.ajax({
				type : "post",
				url : "${contextPath}/post/P_recommand",
				data : {post_id : post_id},
				success : function(data) {
					location.reload();
				}, error : function(data){
					alert("실패"+data);
				}
			});
		});
	});
	
	function deletePost() {
		var post_id = ${P_detail.post_id };
		var brd_id = ${P_detail.brd_id};
		$.ajax({
			type:"post",
			url:"${contextPath}/post/P_delete",
			data:{post_id:post_id,
				  brd_id:brd_id
				},
				success:function(data){
					location.href="${contextPath}/post/P_list?brd_id=${P_detail.brd_id}";
			}	
		});
	}
	

	$(document).ready(function() {
		$("#deletePost").click(function() {
			var result = confirm("글을 삭제하시겠습니까?");

			if (result) {
				deletePost();
			} 
		})
	})

</script>
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
<body class="">
	<!-- side bar -->
	<%@ include file="../include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content" style="width: 1644px">
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
						<h3 class="text-white mb-0">자유게시판</h3>
					</div>
					<div class="table-responsive">
						<table class="table align-items-center table-dark table-flush">
							<colgroup>
								<col style="width: 7%">
								<col style="width: 64%">
								<col style="width: 7%">
								<col style="width: 7%">
								<col style="width: 7%">
								<col style="width: 8%">
							</colgroup>
							<thead class="thead-dark">
								<tr>
									<th scope="col" style="font-size: 15px">글번호</th>
									<th scope="col" style="font-size: 15px">글제목</th>
									<th scope="col" style="font-size: 15px">글 작성자</th>
									<th scope="col" style="font-size: 15px">조회수</th>
									<th scope="col" style="font-size: 15px">추천수</th>
									<th scope="col" style="font-size: 15px">작성시간</th>
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
									<td
										style="font-size: 20px; height: 556px; padding-top: 0px; padding-bottom: 470px;"><span
										class="badge badge-dot mr-4"> <i class="mb-0 text-lg"></i>
											${P_detail.post_content }
									</span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="row" style="marjin-left: 0px;">
						<div align="right" class="col-6">
							<button type="button" class="btn btn-primary" id="P_recommand">추천하기</button>
						</div>
						<c:if test="${useridd eq P_detail.mem_userid }">
							<div align="right" class="col-6">
								<a
									href="${contextPath }/post/P_update?post_id=${P_detail.post_id}&brd_id=${P_detail.brd_id}"><button
										type="button" class="btn btn-primary">수정하기</button></a>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" id="deletePost">삭제하기</button>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div id="R_list">
				<%@ include file="../reply/R_list.jsp"%>
		</div>
		<!-- footer -->
		<%@ include file="../include/main_footer.jsp"%>
		<!-- end footer -->
		<!-- end body -->
	</div>
</body>

<%@ include file="P_delete.jsp"%>
</html>