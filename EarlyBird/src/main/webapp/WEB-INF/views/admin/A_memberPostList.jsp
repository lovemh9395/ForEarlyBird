<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="table-responsive" id="posttable">
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="allChkPost"
						name="allChkPost" /></th>
					<th scope="col">번호</th>
					<th scope="col">대 분류</th>
					<th scope="col">카테고리</th>
					<th scope="col">제목</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
					<th scope="col">삭제여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList }" var="list" varStatus="status">
					<tr>
						<td><input type="checkbox" name="chkPost"
							value="${list.post_id }"><input type="hidden"
							id="chkPost_${list.post_id }" value="${list.post_del}"></td>
						<td>${list.post_id }</td>
						<!-- 글 번호 -->
						<td>${list.large_name }</td>
						<!-- 대분류 -->
						<td>${list.category_name }</td>
						<!-- 카테고리 -->
						<td class="left"><a
							href="${contextPath }/post/P_detail?post_id=${list.post_id }">${list.post_title }</a></td>
						<!-- 제목 -->
						<td>${list.post_simpletime }</td>
						<!-- 작성일 -->
						<td class="right">${list.post_hit }</td>
						<!-- 조회 -->
						<td><c:if test="${list.post_del eq 1}">삭제</c:if> <c:if
								test="${list.post_del eq 0}">표시</c:if></td>
						<!-- 삭제 여부 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
