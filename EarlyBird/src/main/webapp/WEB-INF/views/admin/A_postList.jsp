<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="section">
	<div class="mTitle">
		<h2>전체 게시물 목록</h2>
	</div>
	<div class="mState">
		<div class="gRight">
			<select class="fSelect" id='eSearchSort' name="searchSort"
				onChange="view_board('submit');" align="absmiddle">
				<option value="" selected="selected">기본정렬
				<option value="H">조회수많은순
			</select> <select class="fSelect" id='list_limit' name="list_limit"
				onChange="view_board('submit');" align="absmiddle">
				<option value="10" selected>10개씩보기
				<option value="20">20개씩보기
				<option value="30">30개씩보기
				<option value="50">50개씩보기
			</select>
		</div>
	</div>
	<div class="mCtrl typeHeader setting">
		<div class="row">
			<div class="col-md-auto">삭제</div>
			<div class="col-md-auto">공지등록</div>
			<div class="col-md-auto">공지해제</div>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<caption>전체 게시물 목록</caption>
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" class="allChk" /></th>
					<th scope="col">번호</th>
					<th scope="col">분류</th>
					<th scope="col" style="display: none;">카테고리</th>
					<th scope="col">제목</th>
					<th scope="col">미리보기</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="bbs_no[]" value="1"
						class="rowChk" /> <input type="hidden" name="no[]" value="1">
						<input type="hidden" id="iBoardNo_1" value="1"> <input
						type="hidden" id="iBoardGroup_1" value="1"> <input
						type="hidden" id="eIsDeleted_1" value="F"> <input
						type="hidden" id="iBoardGroup_1" value="1"> <input
						type="hidden" id="iNotice_1" value="F"> <input
						type="hidden" id="iFixed_1" value="F"></td>
					<!-- 번호 -->
					<td>1</td>
					<!-- 분류명 -->
					<td>공지사항</td>
					<!-- 카테고리 -->
					<td style="display: none;"></td>
					<!-- 제목 -->
					<td class="left">몰 오픈을 축하합니다.</td>
					<!-- 미리보기 -->
					<td><span>미리보기</span></td>
					<!-- 작성자 -->
					<td><input type="hidden" id="writer_type_1" value="">
						EC Hosting <br /> (비회원)</td>
					<!-- 작성일 -->
					<td>2019-07-25 07:12:13</td>
					<!-- 조회 -->
					<td class="right">0</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="mPaginate">
		<span>첫</span> / <span>이전 10</span>/ 1 /<span>다음 10</span> / <span>마지막</span>
	</div>
</div>
</div>