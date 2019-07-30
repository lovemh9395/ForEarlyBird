<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<input type='hidden' name='mode' value=''> <input type=hidden
		id='EC_SDE_SHOP_NUM' value='1'> <input type=hidden
		id="isFloatingNumber" value=F> <input type=hidden
		id="sNewboardAnd12rFlag" value=T> <input type=hidden
		id="sIsUse12R" value=T> <input type=hidden id="MALL_ID"
		value=neomart> <input type=hidden id="PRODUCT_VER" value=2>
	<input type=hidden id="hiddenToday" value="2019-07-25"> <input
		type="hidden" name="period" value="30"> <input type="hidden"
		id="navi_hide" name="navi_hide" value="">

	<div class="headingArea">
		<div class="mTitle">
			<h1>게시물 관리</h1>
		</div>
	</div>

	<div class="section">
		<div class="table-responsive">
			<table class="table align-items-center table-flush">
				<colgroup>
					<col style="width: 145px;">
					<col style="width: auto;">
					<col style="width: 145px;">
					<col style="width: auto;">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">기간</th>
						<td colspan="3">
							<div class="row">
								<div class="col-md-1">오늘</div>
								<div class="col-md-1">3일</div>
								<div class="col-md-1">7일</div>
								<div class="col-md-1">1개월</div>
								<div class="col-md-2">2019-06-25</div>
								<div class="col-md-1">달력</div>
								<div class="col-md-2">2019-07-25</div>
								<div class="col-md-1">달력</div>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">게시판 선택</th>
						<td><select class="fSelect" id='sel_board_no'
							name="sel_board_no">
								<option value="">전체목록</option>
								<option value="1">공지사항</option>
								<option value="4">상품 사용후기</option>
								<option value="6">상품 Q&A</option>
								<option value="5">자유게시판</option>
								<option value="8">갤러리</option>
								<option value="2">뉴스/이벤트</option>
								<option value="3">이용안내 FAQ</option>
								<option value="3001">자유게시판3</option>
								<option value="9">1:1 맞춤상담</option>
								<option value="101">상품자유게시판</option>
								<option value="1002">자유게시판2</option>
								<option value="7">자료실</option>
								<option value="1001">한줄메모</option>
						</select> <select class="fSelect" id="board_category" name="board_category"
							style="display: none;">
								<option value="">카테고리 전체</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">게시글 찾기</th>
						<td colspan="3"><select id="search" name="search"
							class="fSelect">
								<option value='subject'>제목</option>
								<option value='content'>내용</option>
								<option value='writer_name'>작성자</option>
								<option value='member_id'>아이디</option>
								<option value='client_ip'>아이피</option>
						</select> <input type="text" id="search_key" name="search_key" value=""
							class="fText" style="width: 400px;" />
					</tr>
					<tr>
						<th scope="row">첨부파일 여부</th>
						<td colspan="3"><label class="gLabel"><input
								type="radio" class="fChk" name="real_filename"
								id="real_filename1" value="" checked /> 전체보기</label> <label
							class="gLabel"><input type="radio" class="fChk"
								name="real_filename" id="real_filename2" value="T" /> 있음</label> <label
							class="gLabel"><input type="radio" class="fChk"
								name="real_filename" id="real_filename3" value="F" /> 없음</label></td>
						<td><input type="button" value=" 검색 "></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<%@include file="A_postList.jsp"%>
	<!-- end of body -->
	<%@include file="include/A_footer.jsp"%>