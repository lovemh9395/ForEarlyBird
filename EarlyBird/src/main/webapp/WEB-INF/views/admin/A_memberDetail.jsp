<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<h1>
		회원 작성 글 보기<small>v1.0</small>
	</h1>
	<h3>${mem_nickname }(${mem_userid })님의 활동기록</h3>
	<h5>작성 글 목록</h5>
	<div id="A_memberPostList">
		<%@include file="A_memberPostList.jsp"%>
	</div>
	<br>
	<h5>작성 댓글 목록</h5>
	<div id="A_memberReplyList">
		<%@include file="A_memberReplyList.jsp"%>
	</div>
</div>
<!-- end of body -->
<%@include file="include/A_footer.jsp"%>