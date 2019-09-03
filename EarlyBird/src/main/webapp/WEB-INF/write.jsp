<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!--   Core   -->
<%@ include file="views/include/core.jsp"%>
<!--  end Core -->
<!-- head -->
<%@ include file="views/include/head.jsp"%>
<!-- end head -->
<script type="text/javascript"
	src="${contextPath }/resources/smarteditor/js/HuskyEZCreator.js"></script>
<style>
.nse_content {
	width: 1650px;
	height: 700px;
}
</style>
<body class="">
	<!-- side bar -->
	<%@ include file="views/include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="views/include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="views/include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<form name="tx_editor_form" id="tx_editor_form" action="/insert.jsp"
			method="post" accept-charset="utf-8">
			<table width="100%">
				<tr>
					<td>제목</td>
					<td><input type="text" id="title" name="title" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td id="editorTd"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="save" value="저장" /> <input
						type="button" value="취소" /></td>
				</tr>
			</table>
		</form>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="views/include/core.jsp"%>
	<!--  end Core -->
</body>
</html>