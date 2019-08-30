<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="row mt-5">
	<div class="col">
		<div class="card bg-default shadow">
			<div class="card-header bg-transparent border-0">
				<div class="row">
					<div class="col-2">
						<h2 class="text-white mb-0">공지글 작성</h2>
					</div>
					<div class="col-10" align="right">
						<button type="button" class="btn btn-primary"
							onclick="makeNotice();">작성하기</button>
						<a href="${contextPath }/admin/A_noticeList"><button
								type="button" class="btn btn-primary">취소하기</button></a>
					</div>
				</div>
			</div>
			<div class="table-responsive">
				<form id="makeNotice" method="post">
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
											id="notice_title" style="width: 1500px;"></span>
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
										<input type="text" id="notice_content"
										style="width: 1500px; height: 500px; font-size: 25px; padding-top: 0px; padding-bottom: 450px;"></span></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	function makeNotice(){
		var notice_title = $("#notice_title").val();
		var notice_content = $("#notice_content").val();
		
		var query = {
				"notice_title":notice_title,
				"notice_content":notice_content
		}
		
		if (confirm("작성하시겠습니까?")) {
			$.ajax({
				async:false,
				type:"post",
				data:query,
				url:"${contextPath}/admin/A_noticeMake",
				success:function(data){
					window.location.href="${contextPath}/admin/A_noticeList";
				}
			});
		}
	}
</script>
<!-- end of body -->
<%@include file="include/A_footer.jsp"%>