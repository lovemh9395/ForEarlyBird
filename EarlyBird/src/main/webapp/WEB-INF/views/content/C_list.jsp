<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../admin/include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<div class="row">
		<div class="col-4">
			<h2>
				컨텐츠 관리<small>ver 1.0</small>
			</h2>
		</div>
		<div class="col-8" align="right">
			<button type="button">컨텐츠 스크래핑</button>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<thead class="thead">
				<tr>
					<th scope="col" class="text-center">대분류</th>
					<th scope="col" class="text-center">분류</th>
					<th scope="col" class="text-center">게시판 ID</th>
					<th scope="col" class="text-center">게시판명</th>
					<th scope="col" class="text-center">링크</th>
					<th scope="col" class="text-center">새글/총 글 갯수</th>
					<th scope="col" class="text-center">게시물 관리</th>
					<th scope="col" class="text-center">사용자 표시여부</th>
				</tr>
			</thead>
			<tbody>
				<!-- c:foreach ex -->
				<tr>
					<td>게임</td>
					<td>사전예약</td>
					<td>1</td>
					<td>사전예약소식</td>
					<td><a href="#">링크</a></td>
					<td>1/1</td>
					<td><a href="C_search"><input type="button" value="글보기"></a>
						<a href="C_make"><input type="button" value="글쓰기"></a> <a
						href="#"><input type="button" value="글삭제"></a>
					<td>표기</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- end of body -->
<%@include file="../admin/include/A_footer.jsp"%>