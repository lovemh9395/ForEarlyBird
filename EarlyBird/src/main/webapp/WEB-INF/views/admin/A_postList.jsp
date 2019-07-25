<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<div>
		<h2>
			게시판 관리<small>ver 1.0</small>
		</h2>
	</div>
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<thead class="thead">
				<tr>
					<th scope="col" class="text-center">대분류</th>
					<th scope="col" class="text-center">분류</th>
					<th scope="col" class="text-center">게시판명</th>
					<th scope="col" class="text-center">게시판 ID</th>
					<th scope="col" class="text-center">권한(읽기/쓰기)</th>
					<th scope="col" class="text-center">새글/총 글 갯수</th>
					<th scope="col" class="text-center">게시판 관리</th>
					<th scope="col" class="text-center">사용자 표시여부</th>
				</tr>
			</thead>
			<tbody>
				<!-- c:foreach ex -->
				<tr>
					<td>게임</td>
					<td>사전예약</td>
					<td>사전예약소식</td>
					<td>1</td>
					<td>관리자/비회원</td>
					<td>1/1</td>
					<td><a href="A_postSearch"><input type="button"
							value="글보기"></a> <a href="#"><input type="button"
							value="글삭제"></a> <a href="#"><input type="button"
							value="공지글"></a>
						<button type="button" data-toggle="modal"
							data-target="#modal-admin">관리자 설정</button>
							<input type="hidden" id="1" value="1"></td>
					<td>표기</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- end of body -->
<jsp:include page="A_adminList.jsp">
	<jsp:param value="1" name="brd_id"/>
</jsp:include>
<%@include file="include/A_footer.jsp"%>