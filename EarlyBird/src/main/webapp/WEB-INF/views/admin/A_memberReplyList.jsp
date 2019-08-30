<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="table-responsive">
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="allChkReply"
						name="allChkReply" /></th>
					<th scope="col">댓글번호 / 글번호</th>
					<th scope="col">작성내용</th>
					<th scope="col">작성일 / 수정일</th>
					<th scope="col">삭제여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${replyList }" var="list" varStatus="status">
					<tr>
						<td><input type="checkbox" name="chkReply"
							value="${list.rpl_id }">
						<td>${list.rpl_id } / ${list.post_id }</td>
						<!-- 댓글 번호 -->
						<td class="left">${list.rpl_content }</td>
						<!-- 제목 -->
						<td>${list.rpl_simpletime }/${list.rpl_updated_simpletime }</td>
						<!-- 작성일 -->
						<td><c:if test="${list.rpl_del eq 1}">삭제</c:if> <c:if
								test="${list.rpl_del eq 0}">표시</c:if></td>
						<!-- 삭제 여부 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>