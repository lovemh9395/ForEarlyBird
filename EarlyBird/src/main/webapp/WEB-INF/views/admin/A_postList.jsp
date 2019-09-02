<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="container">
	<div>
		<div class="row">
			<div class="col-md-auto">
				<h2>전체 게시물 목록</h2>
			</div>
			<div class="col-md-auto">
				<input type="button" value="선택삭제" id="deleteWhatSelected">
			</div>
			<div class="col-md-auto">
				<input type="button" value="삭제해제" id="reViewWhatSelected">
			</div>
			<div class="col-md-auto">
				<input type="button" value="공지등록/해제"
					id="changeNoticeParamWhatSelected">
			</div>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="allChk"
						name="allChk" /> <input type="hidden" id="error"
						value="${error }"></th>
					<th scope="col">번호</th>
					<th scope="col">대 분류</th>
					<th scope="col">카테고리</th>
					<th scope="col">제목</th>
					<th scope="col">공지 / 삭제</th>
					<th scope="col">닉네임 / 아이디</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList }" var="list" varStatus="status">
					<tr>
						<td><input type="checkbox" name="chk"
							value="${list.post_id }"> <input type="hidden"
							id="chk_${list.post_id }" value="${list.post_notice }">
						<td>${list.post_id }</td>
						<!-- 글 번호 -->
						<td>${list.large_name }</td>
						<!-- 대분류 -->
						<td>${list.category_name }</td>
						<!-- 카테고리 -->
						<td class="left">${list.post_title }</td>
						<!-- 제목 -->
						<td><c:if test="${list.post_notice ==1 }">Y</c:if> <c:if
								test="${list.post_notice ==0 }">N</c:if> / <c:if
								test="${list.post_del ==1 }">Del</c:if> <c:if
								test="${list.post_del ==0 }">No</c:if></td>
						<!-- 공지여부 -->
						<td>${list.mem_nickname }/${list.mem_userid }</td>
						<!-- 작성자 아이디-->
						<td>${list.post_simpletime }</td>
						<!-- 작성일 -->
						<td class="right">${list.post_hit }</td>
						<!-- 조회 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
