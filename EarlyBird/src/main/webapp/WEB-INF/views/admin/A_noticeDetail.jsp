<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="row mt-5">
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
									<span class="mb-0 text-lg">${post.post_id }</span>
								</div>
							</th>
							<th scope="row">
								<div class="media-body">
									<span class="mb-0 text-lg">${post.post_title }</span>
								</div>
							</th>
							<td>
								<div class="avatar-group">
									<span class="mb-0 text-lg">${post.mem_userid }</span>
								</div>
							</td>
							<td><span class="badge badge-dot mr-4"> <i
									class="bg-warning"></i> ${post.post_hit }
							</span></td>
							<td><span class="badge badge-dot mr-4"> <i
									class="bg-warning"></i> ${post.post_like }
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
									${post.post_content }
							</span></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row" style="marjin-left: 0px;">
				<c:if test="${useridd eq post.mem_userid }">
					<div align="right" class="col-6">
						<a href="${contextPath }/admin/P_update?post_id=${post.post_id}"><button
								type="button" class="btn btn-primary">수정하기</button></a> <a
							href="${contextPath }/admin/A_noticeList"><button
								type="button" class="btn btn-primary">돌아가기</button> </a>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- end of body -->
<%@include file="include/A_footer.jsp"%>