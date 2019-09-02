<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
		$(document).ready(function() {
			$("#addR_make").click(function() {
				if($("#replyPost_content").val() != ""){
				var rpl_content = $("#replyPost_content").val();
				rpl_content.replace('\r\n', '<br>');
				var post_id =<%=request.getParameter("post_id")%>;
				$.ajax({
					async : true,
					type : "post",
					url : "${contextPath}/reply/R_make",
					data : {
						rpl_content : rpl_content,
						post_id : post_id
					},
					success : function(data) {
						 $("#R_list").html(data);
					}
				});
			} else {
				alert("댓글을 입력해주세요.");
			}
		});
	});
</script>
<div class="row mt-5">
	<div class="col">
		<div class="card bg-default shadow">
			<div class="table-responsive">
				<form id="R_make" name="R_make" method="post"
					action="${contextPath }/reply/R_make">
					<table class="table align-items-center table-dark table-flush">
						<colgroup>
							<col style="width: 100%">
						</colgroup>
						<thead class="thead-dark">
							<tr>
								<th scope="col" style="font-size: 30px">
									<div class="row" style="width: 1600px">
										<div class="col-1">댓글 작성</div>
										<c:if test="${useridd ne null }">
											<div class="col-11" align="right"
												style="word-break: break-all;">
												<button type="button" id="addR_make" class="btn btn-primary">작성하기</button>
											</div>
										</c:if>
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="font-size: 20px; height: 150px"><span
									class="badge badge-dot mr-4"> <i class="mb-0 text-lg"></i>
										<c:if test="${useridd ne null }">
											<textarea class="form-control" id="replyPost_content"
												style="width: 1400px; height: 100px; font-size: 25px; padding-top: 0px; padding-bottom: 0px;"></textarea>
										</c:if> <c:if test="${useridd eq null }">댓글 작성을 하시려면 로그인이 필요합니다.<button
												type="button" class="btn btn-block btn-info"
												data-toggle="modal" data-target="#modal-login">로그인하기</button>
										</c:if></span></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>